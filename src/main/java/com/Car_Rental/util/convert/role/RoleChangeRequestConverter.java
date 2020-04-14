package com.Car_Rental.util.convert.role;

import com.Car_Rental.controller.requests.role.RoleUpdateRequest;
import com.Car_Rental.entity.MRoles;
import com.Car_Rental.exceptions.EntityNotFoundException;
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
