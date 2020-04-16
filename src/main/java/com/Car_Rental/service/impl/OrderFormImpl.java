package com.Car_Rental.service.impl;

import com.Car_Rental.controller.requests.order.OrderCreateRequest;
import com.Car_Rental.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental.entity.CarModel;
import com.Car_Rental.entity.Order;
import com.Car_Rental.entity.User;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.repository.springdata.ModelCarRepository;
import com.Car_Rental.repository.springdata.OrderRepository;
import com.Car_Rental.repository.springdata.UserRepository;
import com.Car_Rental.service.OrderForm;
import com.Car_Rental.util.ValueCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderFormImpl implements OrderForm {

    private final ModelCarRepository modelCarRepository;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    CarModel findCar(Long idCarModel) {
        return modelCarRepository.findById(idCarModel)
                .orElseThrow(() -> new EntityNotFoundException(CarModel.class, idCarModel));
    }

    User findUser(Long idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException(User.class, idUser));
    }

    @Override
    public Order save(OrderCreateRequest request) {

        Order order = new Order();
        order.setOrderCarId(findCar(request.getIdCar()));
        order.setOrderUserId(findUser(request.getIdUser()));
        order.setRentalStart(request.getRentalStart());
        order.setRentalStartTime(request.getRentalStartTime());
        order.setRentalEnd(request.getRentalEnd());
        order.setRentalEndTime(request.getRentalEndTime());
        order.setOrderPrice(ValueCalculation.orderValueCalculation(request.getRentalStart(), request.getRentalStartTime(), request.getRentalEnd(), request.getRentalEndTime(), order.getOrderCarId()));
        if (ValueCalculation.reserveCheck(request.getRentalStart(), request.getRentalEnd(), request.getIdCar()))
            return orderRepository.saveAndFlush(order);
        else return null;
    }

    @Override
    public Order update(OrderUpdateRequest request, Long id) {
        Order order = new Order();
        if (request.getOrderId() != null) {
            order = orderRepository.findById(Long.valueOf(request.getOrderId()))
                    .orElseThrow(() -> new EntityNotFoundException(Order.class, request.getOrderId()));
        }
        order.setOrderUserId(findUser(request.getIdUser()));
        order.setRentalStart(request.getRentalStart());
        order.setRentalStartTime(request.getRentalStartTime());
        order.setRentalEnd(request.getRentalEnd());
        order.setRentalEndTime(request.getRentalEndTime());
        order.setOrderPrice(ValueCalculation.orderValueCalculation(request.getRentalStart(), request.getRentalStartTime(), request.getRentalEnd(), request.getRentalEndTime(), order.getOrderCarId()));
        return orderRepository.saveAndFlush(order);
    }
}