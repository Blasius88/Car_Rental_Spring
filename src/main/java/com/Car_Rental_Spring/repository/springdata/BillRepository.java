package com.Car_Rental_Spring.repository.springdata;


import com.Car_Rental_Spring.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>, CrudRepository<Bill, Long> {

}
