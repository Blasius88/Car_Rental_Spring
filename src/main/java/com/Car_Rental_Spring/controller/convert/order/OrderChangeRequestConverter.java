package com.Car_Rental_Spring.controller.convert.order;

import com.Car_Rental_Spring.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental_Spring.domain.Order;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class OrderChangeRequestConverter extends OrderRequestConverter<OrderUpdateRequest, Order> {

    @Override
    public Order convert(OrderUpdateRequest source) {
        Order user =
                ofNullable(entityManager.find(
                        Order.class, source.getOrderId()))
                        .orElseThrow(() -> new EntityNotFoundException(
                                Order.class, source.getOrderId()));
        return doConvert(user, source);
    }
}
