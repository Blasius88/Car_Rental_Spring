package com.Car_Rental_Spring.controller.convert.order;

import com.Car_Rental_Spring.controller.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.domain.Order;

public abstract class OrderRequestConverter<S, T> extends EntityConverter<S, T> {
    protected Order doConvert(Order order, OrderCreateRequest request) {
        order.setOrderUserId(request.getIdUser());
        order.setOrderCarId(request.getIdCar());
        order.setOrderWorkerId(request.getIdWorker());
        order.setRentalStart(request.getRentalStart());
        order.setRentalEnd(request.getRentalEnd());
        return order;
    }
}
