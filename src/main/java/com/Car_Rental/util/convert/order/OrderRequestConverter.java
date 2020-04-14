package com.Car_Rental.util.convert.order;

import com.Car_Rental.controller.requests.order.OrderCreateRequest;
import com.Car_Rental.entity.Order;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class OrderRequestConverter implements Converter<OrderCreateRequest, Order> {

    @Override
    public Order convert(OrderCreateRequest entity) {
        Order order = new Order();
        order.setRentalStart(entity.getRentalStart());
        order.setRentalStartTime(entity.getRentalStartTime());
        order.setRentalEnd(entity.getRentalEnd());
        order.setRentalEndTime(entity.getRentalEndTime());
        return order;
    }

    public Order convertUpdate(OrderCreateRequest entity, Order order) {
        order.setRentalStart(entity.getRentalStart());
        order.setRentalStartTime(entity.getRentalStartTime());
        order.setRentalEnd(entity.getRentalEnd());
        order.setRentalEndTime(entity.getRentalEndTime());
        return order;
    }

}
