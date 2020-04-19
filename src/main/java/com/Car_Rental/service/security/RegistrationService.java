package com.Car_Rental.service.security;

import com.Car_Rental.config.security.jwt.JwtTokenUtils;
import com.Car_Rental.controller.requests.authentication.AuthenticationRequest;
import com.Car_Rental.controller.requests.user.UserCreateRequest;
import com.Car_Rental.entity.User;
import com.Car_Rental.exceptions.UserRegistrationException;
import com.Car_Rental.repository.springdata.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final JwtTokenUtils tokenProvider;

    private final UserRepository userRepository;

    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    public void notConfirmedRegistration(UserCreateRequest request) {

        if (userRepository.findUserByLogin(request.getLogin().toLowerCase()).isPresent()) {
            throw new UserRegistrationException("User already exists");
        } else if (userRepository
                .findUserByLogin(request.getLogin().toLowerCase())
                .isPresent()) {
            throw new UserRegistrationException("User exists but not confirmed");
        } else {
            User user = new User();
            String token = tokenProvider.generateConfirmationToken(request);
            BeanUtils.copyProperties(request, user, "id", "confirmationToken");
            user.setConfirmationToken(token);
            user.setLogin(user.getLogin().toLowerCase());
            userRepository.saveAndFlush(user);
        }
    }

    public AuthenticationRequest confirm(String token) {
        AuthenticationRequest request = new AuthenticationRequest();
        if (StringUtils.hasText(token) /*&& tokenProvider.validateToken(token)*/) {
            String login = tokenProvider.getUsernameFromToken(token);
            Optional<User> user =
                    userRepository.findUserByLogin(login.toLowerCase());
            UserCreateRequest userCreateRequest = new UserCreateRequest();
            BeanUtils.copyProperties(user.get(), userCreateRequest, "id", "confirmationToken");
            userRepository.delete(user.get());
            userRepository.saveAndFlush(
                    Objects.requireNonNull(conversionService.convert(userCreateRequest, User.class)));
            request.setLogin(user.get().getLogin());
            request.setPassword(user.get().getPassword());
        }
        return request;
    }
}
