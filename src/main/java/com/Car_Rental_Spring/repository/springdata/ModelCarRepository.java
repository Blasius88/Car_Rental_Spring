package com.Car_Rental_Spring.repository.springdata;

import com.Car_Rental_Spring.entity.Car_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ModelCarRepository extends JpaRepository<Car_Model, Long>, CrudRepository<Car_Model, Long> {

}
