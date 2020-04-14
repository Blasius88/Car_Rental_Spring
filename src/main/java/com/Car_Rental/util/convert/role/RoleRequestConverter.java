package com.Car_Rental.util.convert.role;

import com.Car_Rental.controller.requests.role.RoleCreateRequest;
import com.Car_Rental.entity.MRoles;
import com.Car_Rental.util.convert.EntityConverter;

public abstract class RoleRequestConverter<S,T> extends EntityConverter<S,T> {

    protected MRoles doConverter(MRoles role, RoleCreateRequest request)
    {
       role.setNameRoles(request.getName());
       return role;
    }
}
