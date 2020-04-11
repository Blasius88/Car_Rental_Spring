package com.Car_Rental_Spring.service.impl;

import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental_Spring.entity.Order;
import com.Car_Rental_Spring.entity.WorkerUser;
import com.Car_Rental_Spring.repository.springdata.ModelCarRepository;
import com.Car_Rental_Spring.repository.springdata.UserRepository;
import com.Car_Rental_Spring.repository.springdata.WorkerRepository;
import com.Car_Rental_Spring.service.ServisUser;
import com.Car_Rental_Spring.util.convert.order.OrderRequestConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional (rollbackFor = Exception.class)
public class ServisUserImpl implements ServisUser {

    private final ModelCarRepository modelCarRepository;

    private final WorkerRepository workerRepository;

    private final UserRepository userRepository;

    private final OrderRequestConverter orderRequestConverter;

    @Override
    public Order save(OrderCreateRequest request) {

        Order order =() orderRequestConverter.convert(request);

        order.setOrderCarId(modelCarRepository.findById(Math.toIntExact(request.getIdCar())));
    }

    @Override
    public Order update(OrderUpdateRequest request, Long id) {
        return null;
    }
}
