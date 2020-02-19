package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.domain.Car_Model;

import java.util.List;

public interface ModelCarDao extends GenericDao<Car_Model, Long> {
    Car_Model findModelName(String name);
}
