package com.Car_Rental.util.convert.user;

import com.Car_Rental.controller.requests.user.UserCreateRequest;
import com.Car_Rental.entity.Roles;
import com.Car_Rental.entity.User;
import com.Car_Rental.exceptions.ConversionException;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.exceptions.ArgumentOfMethodNotValidException;
import com.Car_Rental.util.convert.EntityConverter;

import javax.persistence.NoResultException;

public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {

    protected User doConvert(User user, UserCreateRequest request) {

        user.setFirstName(request.getUserFirstName());
        user.setLastName(request.getUserLastName());
        user.setLogin(request.getLogin());
        user.setUserCreated(request.getCreated());
        user.setPassword(request.getPassword());
        user.setUserEmail(request.getEmail());
        user.setUserPhone(request.getPhone());
        user.setUserCity(request.getCity());
        return user;
    }
}
