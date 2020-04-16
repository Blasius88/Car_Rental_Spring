package com.Car_Rental.repository.springdata;

import com.Car_Rental.entity.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long>, CrudRepository<CarBrand, Long> {
}
