package com.Car_Rental.repository.hibernate;

import com.Car_Rental.entity.Roles;

import java.util.List;

public interface RoleDao extends GenericDao<Roles, Long > {
    Roles findRole(String str);
    List<Roles> getRolesByUserId(int userId);

}