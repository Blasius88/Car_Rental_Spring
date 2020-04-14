package com.Car_Rental.repository.springdata;

import com.Car_Rental.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>, CrudRepository<Color, Long> {

}
