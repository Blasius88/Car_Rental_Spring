package com.Car_Rental.controller;

import com.Car_Rental.controller.requests.user.UserCreateRequest;
import com.Car_Rental.controller.requests.user.UserUpdateRequest;
import com.Car_Rental.entity.User;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.repository.springdata.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rest/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    @Qualifier(value = "mvcConversionService")
    private ConversionService conversionService;

    @ApiOperation(value = "Get all users from server")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting data"),
            @ApiResponse(code = 400, message = "Something wrong"),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Users not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get user from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting user"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(
            @ApiParam("User Path Id") @PathVariable String id) {
        User user = userRepository
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Create user from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful create user"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @PostMapping
    @Transactional()
    public ResponseEntity<User> createUser(
            @RequestBody @Valid UserCreateRequest request) {
        User user = conversionService.convert(request, User.class);
        return new ResponseEntity<>(userRepository.saveAndFlush(user), CREATED);
    }

    @ApiOperation(value = "Update user by userId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping("/update/{id}")
    @Transactional
    public ResponseEntity<User> updateUser(
            @ModelAttribute @Valid UserUpdateRequest request) {
        User convertedUser = conversionService.convert(request, User.class);
        return new ResponseEntity<>(userRepository.save(convertedUser), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleting user"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteUserById(
            @ApiParam("User Path Id") @PathVariable("id") String id) {
        userRepository.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}