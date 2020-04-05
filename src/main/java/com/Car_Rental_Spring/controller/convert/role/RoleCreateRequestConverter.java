package com.Car_Rental_Spring.controller.convert.role;

import com.Car_Rental_Spring.controller.requests.role.RoleCreateRequest;
import com.Car_Rental_Spring.domain.MRoles;
import org.springframework.stereotype.Component;

@Component
public class RoleCreateRequestConverter extends RoleRequestConverter<RoleCreateRequest, MRoles> {
    @Override
    public MRoles convert(RoleCreateRequest source) {
        MRoles mRoles = new MRoles();
        return doConverter(mRoles, source);
    }
}
