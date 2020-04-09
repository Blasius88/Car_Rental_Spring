package com.Car_Rental_Spring.repository.springdata;

import com.Car_Rental_Spring.entity.Car_Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepository extends JpaRepository<Car_Brand, Long>, CrudRepository<Car_Brand, Long> {
}
