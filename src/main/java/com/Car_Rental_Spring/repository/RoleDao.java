package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.entity.MRoles;

import java.util.List;

public interface RoleDao extends GenericDao<MRoles, Long > {
    MRoles findRole(String str);
    List<MRoles> getRolesByUserId(int userId);

}