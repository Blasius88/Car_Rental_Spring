package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.domain.Color;

import java.util.List;

public interface ColorDao extends GenericDao<Color, Long> {
    Color findColor (String str);
}
