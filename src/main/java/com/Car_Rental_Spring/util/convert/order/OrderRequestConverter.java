package com.Car_Rental_Spring.util.convert.order;

import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.NoResultException;

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
