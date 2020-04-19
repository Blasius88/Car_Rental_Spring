package com.Car_Rental.repository.springdata;

import com.Car_Rental.entity.CarModel;
import com.Car_Rental.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long>, CrudRepository<Order, Long> {

    List<Order> findByOrderCarId(Long id);

    @Query("select '*' from Order o where o.orderCarId =:id")
    List<Order> reserveCheck(Long id);
}