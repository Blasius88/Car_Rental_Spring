package com.Car_Rental.repository.springdata;

import com.Car_Rental.entity.MRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<MRoles, Long>, CrudRepository<MRoles, Long> {

}
