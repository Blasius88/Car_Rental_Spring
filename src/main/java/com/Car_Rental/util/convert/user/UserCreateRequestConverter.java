package com.Car_Rental.util.convert.user;

import com.Car_Rental.controller.requests.user.UserCreateRequest;
import com.Car_Rental.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateRequestConverter extends UserRequestConverter<UserCreateRequest, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User convert(UserCreateRequest request) {
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(findRole(request.getClass(), request.getIdRole()));

        return doConvert(user, request);
    }
}
