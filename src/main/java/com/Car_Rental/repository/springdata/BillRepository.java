package com.Car_Rental.repository.springdata;


import com.Car_Rental.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>, CrudRepository<Bill, Long> {

}
