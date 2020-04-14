package com.Car_Rental.repository.hibernate;

import com.Car_Rental.entity.MRoles;

import java.util.List;

public interface RoleDao extends GenericDao<MRoles, Long > {
    MRoles findRole(String str);
    List<MRoles> getRolesByUserId(int userId);

}