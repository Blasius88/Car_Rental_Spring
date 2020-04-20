package com.Car_Rental.service;

import com.Car_Rental.controller.requests.order.OrderCreateRequest;
import com.Car_Rental.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental.entity.Order;
import com.Car_Rental.exceptions.DateOrTimeEnteredIncorrectly;

import java.util.List;

public interface OrderForm {

    Order save (OrderCreateRequest request) throws DateOrTimeEnteredIncorrectly;

    Order update (OrderUpdateRequest request, Long id);
}
