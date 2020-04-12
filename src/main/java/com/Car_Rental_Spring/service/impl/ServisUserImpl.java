package com.Car_Rental_Spring.service.impl;

import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental_Spring.entity.Order;
import com.Car_Rental_Spring.repository.springdata.ModelCarRepository;
import com.Car_Rental_Spring.repository.springdata.UserRepository;
import com.Car_Rental_Spring.repository.springdata.WorkerRepository;
import com.Car_Rental_Spring.service.ServisUser;
import com.Car_Rental_Spring.util.convert.order.OrderRequestConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;


@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ServisUserImpl implements ServisUser {

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
            order.setRentalEnd(request.getRentalEnd());
            order.setOrderPrice(orderValueCalculation(request.getRentalStart(), request.getRentalEnd()));
            return order;
        } catch (Exception e) {
        }
        return order;
    }

    @Override
    public Order update(OrderUpdateRequest request, Long id) {
        return null;
    }

    private static double orderValueCalculation(Timestamp s1, Timestamp s2) {
        double sum = 0.0;
      //  String [] strMas = s1.split("[. / ,]");
        //int rentalStart = Integer.parseInt(s1);

        return sum;
    }
}