package com.Car_Rental_Spring.repository.springdata;

import com.Car_Rental_Spring.domain.MRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<MRoles, Long>, CrudRepository<MRoles, Long> {

}