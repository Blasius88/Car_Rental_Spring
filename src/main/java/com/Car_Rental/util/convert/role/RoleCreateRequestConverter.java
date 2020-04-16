package com.Car_Rental.util.convert.role;

import com.Car_Rental.controller.requests.role.RoleCreateRequest;
import com.Car_Rental.entity.Roles;
import org.springframework.stereotype.Component;

@Component
public class RoleCreateRequestConverter extends RoleRequestConverter<RoleCreateRequest, Roles> {
    @Override
    public Roles convert(RoleCreateRequest source) {
        Roles roles = new Roles();
        return doConverter(roles, source);
    }
}
