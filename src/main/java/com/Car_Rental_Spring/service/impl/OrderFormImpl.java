package com.Car_Rental_Spring.service.impl;

import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental_Spring.entity.Car_Model;
import com.Car_Rental_Spring.entity.Order;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.entity.WorkerUser;
import com.Car_Rental_Spring.exceptions.DateOrTimeEnteredIncorrectly;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.repository.springdata.ModelCarRepository;
import com.Car_Rental_Spring.repository.springdata.OrderRepository;
import com.Car_Rental_Spring.repository.springdata.UserRepository;
import com.Car_Rental_Spring.repository.springdata.WorkerRepository;
import com.Car_Rental_Spring.service.OrderForm;
import com.Car_Rental_Spring.util.convert.order.OrderRequestConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderFormImpl implements OrderForm {

    private final ModelCarRepository modelCarRepository;

    private final WorkerRepository workerRepository;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    Car_Model findCar(Long idCarModel) {
        return modelCarRepository.findById(idCarModel)
                .orElseThrow(() -> new EntityNotFoundException(Car_Model.class, idCarModel));
    }

    User findUser(Long idUser) {
    return userRepository.findById(idUser)
            .orElseThrow(()-> new EntityNotFoundException(User.class, idUser));
    }

    WorkerUser findWorker(Long idWorker) {
        return  workerRepository.findById(idWorker)
                .orElseThrow(()-> new EntityNotFoundException(WorkerUser.class, idWorker));
    }

    @Override
    public Order save(OrderCreateRequest request) {

        Order order = new Order();
        order.setOrderCarId(findCar(request.getIdCar()));
        order.setOrderUserId(findUser(request.getIdUser()));
        order.setOrderWorkerId(findWorker(request.getIdWorker()));
        order.setRentalStart(request.getRentalStart());
        order.setRentalStartTime(request.getRentalStartTime());
        order.setRentalEnd(request.getRentalEnd());
        order.setRentalEndTime(request.getRentalEndTime());
        order.setOrderPrice(orderValueCalculation(request.getRentalStart(), request.getRentalStartTime(), request.getRentalEnd(), request.getRentalEndTime(), order.getOrderCarId()));
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order update(OrderUpdateRequest request, Long id) {
        return null;
    }

    private static double orderValueCalculation(String startData,  String startTime,String endData, String endTime, Car_Model car_model) {
        try {
            boolean flag = false;
            double sum = 0.0;
            double hour = 0.0;
            int minStart = 0;
            int minEnd = 0;
            Long startDataMin = dataParsing(startData) + timeParsing(startTime);
            Long endDataMin = dataParsing(endData) + timeParsing(endTime);
            if ((endDataMin - startDataMin) < 0)
                throw new DateOrTimeEnteredIncorrectly();
            else {
                hour = (endDataMin - startDataMin) / 60;
                sum = car_model.getPrice_hour() * hour;
            }
            double price = car_model.getPrice_hour();
            sum = hour * price;
            return sum;
        } catch (Exception e) {

            return 0.0;
        }
    }

    private static Long dataParsing(String data) {
        String[] strMas = data.split("/");
        Long[] mas1 = new Long[strMas.length];
        Long minStart = 0L;
        for (int i = 0; i < strMas.length; i++) {
            mas1[i] = Long.parseLong(strMas[i]);
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

    private static Long timeParsing(String time) {
        String[] strMas = time.split(":");
        Long[] masStartTime = new Long[strMas.length];
        for (int i = 0; i < strMas.length; i++) {
            masStartTime[i] = Long.parseLong(strMas[i]);
        }
        Long minStart = 0L;
        minStart = masStartTime[0]*60 + masStartTime[1];

        return minStart;
    }
}