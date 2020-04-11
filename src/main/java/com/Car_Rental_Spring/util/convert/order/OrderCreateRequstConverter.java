package com.Car_Rental_Spring.util.convert.order;

import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.entity.Order;
import org.springframework.stereotype.Component;

import javax.persistence.ManyToOne;

@Component
public class OrderCreateRequstConverter extends OrderRequestConverter<OrderCreateRequest, Order> {
    @Override
    public Order convert(OrderCreateRequest source) {
        Order order = new Order();
        order.setOrderCarId(findCar(source.getClass(), Math.toIntExact(source.getIdCar())));
        order.setOrderUserId(findUser(source.getClass(), Math.toIntExact(source.getIdUser())));
        order.setOrderWorkerId(findWorker(source.getClass(), Math.toIntExact(source.getIdWorker())));
        return doConvert(order, source);
    }
}
