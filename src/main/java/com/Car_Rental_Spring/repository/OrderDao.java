package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.domain.Order;

public interface OrderDao extends GenericDao<Order, Long> {
    Order findOrder(String str);
}
