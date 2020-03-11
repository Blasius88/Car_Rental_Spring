package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.domain.Car_Brand;

import java.util.List;

public interface Car_BrandDao extends GenericDao<Car_Brand, Long> {
        List<Car_Brand> findPrice (double sum);
        Car_Brand findCarBrand (String str);

}
