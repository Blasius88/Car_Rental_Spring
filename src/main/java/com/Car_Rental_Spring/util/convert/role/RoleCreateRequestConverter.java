package com.Car_Rental_Spring.util.convert.role;

import com.Car_Rental_Spring.controller.requests.role.RoleCreateRequest;
import com.Car_Rental_Spring.entity.MRoles;
import org.springframework.stereotype.Component;

@Component
public class RoleCreateRequestConverter extends RoleRequestConverter<RoleCreateRequest, MRoles> {
    @Override
    public MRoles convert(RoleCreateRequest source) {
        MRoles mRoles = new MRoles();
        return doConverter(mRoles, source);
    }
}
