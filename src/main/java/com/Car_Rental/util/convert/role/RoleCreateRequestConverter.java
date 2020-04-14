package com.Car_Rental.util.convert.role;

import com.Car_Rental.controller.requests.role.RoleCreateRequest;
import com.Car_Rental.entity.MRoles;
import org.springframework.stereotype.Component;

@Component
public class RoleCreateRequestConverter extends RoleRequestConverter<RoleCreateRequest, MRoles> {
    @Override
    public MRoles convert(RoleCreateRequest source) {
        MRoles mRoles = new MRoles();
        return doConverter(mRoles, source);
    }
}
