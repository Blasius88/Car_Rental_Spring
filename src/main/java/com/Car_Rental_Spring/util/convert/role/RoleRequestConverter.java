package com.Car_Rental_Spring.util.convert.role;

import com.Car_Rental_Spring.util.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.role.RoleCreateRequest;
import com.Car_Rental_Spring.entity.MRoles;

public abstract class RoleRequestConverter<S,T> extends EntityConverter<S,T> {

    protected MRoles doConverter(MRoles role, RoleCreateRequest request)
    {
       role.setName_roles(request.getName());
       return role;
    }
}
