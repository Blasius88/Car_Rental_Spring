package com.Car_Rental_Spring.controller.convert.order;

import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateRequstConverter extends OrderRequestConverter<OrderCreateRequest, Order>{
    @Override
    public Order convert(OrderCreateRequest source) {
        Order order = new Order();
        return doConvert(order, source);
    }
}
