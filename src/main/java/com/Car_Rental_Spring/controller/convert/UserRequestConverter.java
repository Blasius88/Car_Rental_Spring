package com.Car_Rental_Spring.controller.convert;

import com.Car_Rental_Spring.controller.requests.UserCreateRequest;
import com.Car_Rental_Spring.domain.User;

public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {

    protected User doConvert(User user, UserCreateRequest request) {

        user.setFirstName(request.getUserFirstName());
        user.setLastName(request.getUserLastName());
        user.setUserLogin(request.getLogin());
        user.setUserPass(request.getPassword());
        user.setUserCreated(request.getCreated());
        user.setIdRole(request.getIdRole());
        user.setUserEmail(request.getEmail());
        user.setUserPhone(request.getPhone());
        user.setUserCity(request.getCity());
        return user;
    }
}
