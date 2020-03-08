package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.domain.Color;
import com.Car_Rental_Spring.domain.User;

import java.util.List;

public interface ColorDao extends GenericDao<Color, Long> {
    List<Color> search(String str, Integer limit, Integer offset);
}
