package com.Car_Rental_Spring.controller.convert.role;

import com.Car_Rental_Spring.controller.requests.role.RoleUpdateRequest;
import com.Car_Rental_Spring.domain.MRoles;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class RoleChangeRequestConverter extends RoleRequestConverter<RoleUpdateRequest, MRoles> {

    @Override
    public MRoles convert(RoleUpdateRequest source) {
        MRoles mRoles =
                ofNullable(entityManager.find(
                        MRoles.class, source.getRoleId()))
                        .orElseThrow(() -> new EntityNotFoundException(
                                MRoles.class, source.getRoleId()));

        return doConverter(mRoles, source);
    }
}
