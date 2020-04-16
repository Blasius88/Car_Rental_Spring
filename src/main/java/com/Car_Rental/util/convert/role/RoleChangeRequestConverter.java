package com.Car_Rental.util.convert.role;

import com.Car_Rental.controller.requests.role.RoleUpdateRequest;
import com.Car_Rental.entity.Roles;
import com.Car_Rental.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class RoleChangeRequestConverter extends RoleRequestConverter<RoleUpdateRequest, Roles> {

    @Override
    public Roles convert(RoleUpdateRequest source) {
        Roles roles =
                ofNullable(entityManager.find(
                        Roles.class, source.getRoleId()))
                        .orElseThrow(() -> new EntityNotFoundException(
                                Roles.class, source.getRoleId()));

        return doConverter(roles, source);
    }
}
