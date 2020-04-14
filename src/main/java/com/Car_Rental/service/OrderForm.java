package com.Car_Rental.service;

import com.Car_Rental.controller.requests.order.OrderCreateRequest;
import com.Car_Rental.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental.entity.Order;

public interface OrderForm {

    Order save (OrderCreateRequest request);

    Order update (OrderUpdateRequest request, Long id);
}
