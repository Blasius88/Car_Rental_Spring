package com.Car_Rental_Spring.repository.springdata;

import com.Car_Rental_Spring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, CrudRepository<Order, Long> {

}