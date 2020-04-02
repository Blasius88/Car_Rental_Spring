package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.user.UserCreateRequest;
import com.Car_Rental_Spring.controller.requests.user.UserUpdateRequest;
import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.repository.springdata.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/rest/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @GetMapping
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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@ApiParam("User Path Id") @PathVariable Long id) {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(() -> new EntityNotFoundException(User.class, id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserCreateRequest request) {
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
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserUpdateRequest request) {
        return new ResponseEntity<>(userRepository.save(conversionService.convert(request, User.class)), HttpStatus.OK);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping("/spring-data/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<User>> getUsersSpringData(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(userRepository.findAll(pageable), HttpStatus.OK);
    }
}