package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.domain.MRoles;
import com.Car_Rental_Spring.repository.GenericDao;

import java.util.List;

public interface RoleDao extends GenericDao<MRoles, Long> {
    List<MRoles> getRolesByUserId(Long userId);
}
