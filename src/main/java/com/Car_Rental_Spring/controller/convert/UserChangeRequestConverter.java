package com.Car_Rental_Spring.controller.convert;

import com.Car_Rental_Spring.controller.requests.UserUpdateRequest;
import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class UserChangeRequestConverter extends UserRequestConverter<UserUpdateRequest, User> {

    @Override
    public User convert(UserUpdateRequest request) {
        User user =
                ofNullable(entityManager.find(User.class, request.getUserId()))
                        .orElseThrow(() -> new EntityNotFoundException(User.class, request.getUserId()));
        return doConvert(user, request);
    }
}
