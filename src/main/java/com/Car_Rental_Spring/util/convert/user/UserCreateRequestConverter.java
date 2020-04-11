package com.Car_Rental_Spring.util.convert.user;

import com.Car_Rental_Spring.controller.requests.user.UserCreateRequest;
import com.Car_Rental_Spring.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateRequestConverter extends UserRequestConverter<UserCreateRequest, User> {

    @Override
    public User convert(UserCreateRequest request) {
        User user = new User();
        user.setRole(findRole(request.getClass() , Math.toIntExact(request.getIdRole())));
        return doConvert(user, request);
    }
}
