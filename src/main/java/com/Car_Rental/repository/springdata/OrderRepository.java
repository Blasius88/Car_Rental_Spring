package com.Car_Rental.repository.springdata;

import com.Car_Rental.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, CrudRepository<Order, Long> {

}