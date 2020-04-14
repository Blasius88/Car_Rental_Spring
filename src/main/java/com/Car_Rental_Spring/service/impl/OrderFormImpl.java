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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.SimpleDateFormat;


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
                .orElseThrow(() -> new EntityNotFoundException(User.class, idUser));
    }

    WorkerUser findWorker(Long idWorker) {
        return workerRepository.findById(idWorker)
                .orElseThrow(() -> new EntityNotFoundException(WorkerUser.class, idWorker));
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

    private static double orderValueCalculation(Date startData, String startTime, Date endData, String endTime, Car_Model car_model) {
        try {
            double sum = 0.0;
            double hour = dataParsing(startData, endData) + (timeParsing(endTime) - timeParsing(startTime));
            double price = car_model.getPrice_hour();
            sum = hour * price;
            return sum;
        } catch (Exception e) {
            return 0.0;
        }
    }

    private static Long dataParsing(Date dateStart, Date dateEnd) {
        Long hours = 0L;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date1 = (Date) dateFormat.parse(String.valueOf(dateStart));
            Date date2 = (Date) dateFormat.parse(String.valueOf(dateEnd));
            long milliseconds = date2.getTime() - date1.getTime();
            if (milliseconds > 0) {
                hours = (milliseconds / (60 * 60 * 1000));
                return hours;
            } else throw new DateOrTimeEnteredIncorrectly();
        } catch (Exception e) {
            return hours;
        }
    }

    private static Long timeParsing(String time) {
        String[] strMas = time.split(":");
        Long[] masStartTime = new Long[strMas.length];
        for (int i = 0; i < strMas.length; i++) {
            masStartTime[i] = Long.parseLong(strMas[i]);
        }
        Long minStart = 0L;
        minStart = masStartTime[0] * 60 + masStartTime[1];

        return minStart;
    }
}