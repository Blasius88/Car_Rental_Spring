package com.Car_Rental.repository.springdata;

import com.Car_Rental.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long>, CrudRepository<Order, Long> {

    @Query("select '*' from Order o where o.orderCarId =:id")
    Set<Order> reserveCheck (Long id);
}