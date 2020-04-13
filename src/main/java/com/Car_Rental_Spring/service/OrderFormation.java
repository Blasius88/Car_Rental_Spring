package com.Car_Rental_Spring.service;

import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental_Spring.entity.Order;

public interface OrderFormation {

    Order save (OrderCreateRequest request);

    Order update (OrderUpdateRequest request, Long id);
}
