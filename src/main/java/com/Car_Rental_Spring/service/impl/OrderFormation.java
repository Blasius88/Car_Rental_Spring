package com.Car_Rental_Spring.service.impl;

import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental_Spring.entity.Car_Model;
import com.Car_Rental_Spring.entity.Order;
import com.Car_Rental_Spring.exceptions.DateOrTimeEnteredIncorrectly;
import com.Car_Rental_Spring.repository.springdata.ModelCarRepository;
import com.Car_Rental_Spring.repository.springdata.UserRepository;
import com.Car_Rental_Spring.repository.springdata.WorkerRepository;
import com.Car_Rental_Spring.util.convert.order.OrderRequestConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class OrderFormation implements com.Car_Rental_Spring.service.OrderFormation {

    private final ModelCarRepository modelCarRepository;

    private final WorkerRepository workerRepository;

    private final UserRepository userRepository;

    private final OrderRequestConverter orderRequestConverter;

    @Override
    public Order save(OrderCreateRequest request) {

        Order order = orderRequestConverter.convert(request);
        try {
            order.setOrderCarId(modelCarRepository.findByIdModel(request.getIdCar()));
            order.setOrderUserId(userRepository.findByIdUser(request.getIdUser()));
            order.setOrderWorkerId(workerRepository.findByIdWorker(request.getIdWorker()));
            order.setRentalStart(request.getRentalStart());
            order.setRentalStartTime(request.getRentalStartTime());
            order.setRentalEnd(request.getRentalEnd());
            order.setRentalEndTime(request.getRentalEndTime());
            order.setOrderPrice(orderValueCalculation(request.getRentalStart(), request.getRentalEnd(), request.getRentalEnd(), request.getRentalEndTime(), order.getOrderCarId()));
            return order;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Order update(OrderUpdateRequest request, Long id) {
        return null;
    }

    private static double orderValueCalculation(String startData, String endData, String startTime, String endTime, Car_Model car_model) {
        try {
            boolean flag = false;
            double sum = 0.0;
            int minStart = 0;
            int minEnd = 0;
            int startDataMin = dataParsing(startData) + timeParsing(startTime);
            int endDataMin = dataParsing(endData) + timeParsing(endTime);
            if ((endDataMin -  startDataMin) < 0)
                throw new DateOrTimeEnteredIncorrectly();
            else {
                double hour = (endDataMin -  startDataMin)/60;
                sum = car_model.getPrice_hour()*hour;
            }
            return sum;
        } catch (Exception e) {
            return 0.0;
        }
    }

    private static int dataParsing(String data) {
        String[] strMas = data.split(":./,-");
        int[] mas1 = new int[strMas.length];
        int minStart = 0;
        for (int i = 0; i < strMas.length; i++) {
            mas1[i] = Integer.parseInt(strMas[i]);
        }
        if (mas1[2] % 4 == 0 && mas1[2] % 100 != 0 || mas1[2] % 400 == 0) {
            if (mas1[1] == 1 && mas1[1] == 3 && mas1[1] == 5 && mas1[1] == 7 && mas1[1] == 8 && mas1[1] == 10 && mas1[1] == 12)
                minStart = mas1[0] * 24 * 60 + mas1[1] * 31 * 24 * 60 + mas1[2] * 12 * 31 * 24 * 60;
            else if (mas1[1] == 2)
                minStart = mas1[0] * 24 * 60 + mas1[1] * 29 * 24 * 60 + mas1[2] * 12 * 31 * 24 * 60;
            else
                minStart = mas1[0] * 24 * 60 + mas1[1] * 30 * 24 * 60 + mas1[2] * 12 * 31 * 24 * 60;
        } else {
            if (mas1[1] == 1 && mas1[1] == 3 && mas1[1] == 5 && mas1[1] == 7 && mas1[1] == 8 && mas1[1] == 10 && mas1[1] == 12)
                minStart = mas1[0] * 24 * 60 + mas1[1] * 31 * 24 * 60 + mas1[2] * 12 * 31 * 24 * 60;
            else if (mas1[1] == 2)
                minStart = mas1[0] * 24 * 60 + mas1[1] * 28 * 24 * 60 + mas1[2] * 12 * 31 * 24 * 60;
            else
                minStart = mas1[0] + mas1[1] * 60 + mas1[0] * 24 * 60 + mas1[1] * 30 * 24 * 60 + mas1[2] * 12 * 31 * 24 * 60;
        }
        return minStart;
    }

    private static int timeParsing(String time) {
        String[] strMas = time.split(":");
        int[] masStartTime = new int[strMas.length];
        for (int i = 0; i < strMas.length; i++) {
            masStartTime[i] = Integer.parseInt(strMas[i]);
        }
        int minStart = 0;
        minStart = masStartTime[0] + masStartTime[1] * 60;

        return minStart;
    }

}