package com.Car_Rental_Spring.controller.convert.user;

import com.Car_Rental_Spring.controller.requests.user.UserCreateRequest;
import com.Car_Rental_Spring.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateRequestConverter extends UserRequestConverter<UserCreateRequest, User> {

    @Override
    public User convert(UserCreateRequest request) {
        User user = new User();
        return doConvert(user, request);
    }
}