package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.SearchCriteria;
import com.Car_Rental_Spring.controller.requests.UserCreateRequest;
import com.Car_Rental_Spring.domain.MRoles;
import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.domain.hibernate.TestUser;
import com.Car_Rental_Spring.repository.RoleDao;
import com.Car_Rental_Spring.repository.UserDao;
import com.Car_Rental_Spring.repository.hibernate.HibernateUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private HibernateUser hibernateUserDao;


    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TestUser>> getUsersHibernate() {
        return new ResponseEntity<>(hibernateUserDao.findAll(), HttpStatus.OK);
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
        User user = userDao.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Autowired
    private RoleDao roleDao;

    /**/
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserCreateRequest request) {
        User user = new User();
        //userID is empty - will be generated by DB
        data(request, user);

        User savedUser = userDao.save(user);
        roleDao.save(new MRoles(savedUser.getUserId(), "ROLE_USER"));

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TestUser> createTestUser(@RequestBody @Valid UserCreateRequest request) {
        TestUser user = new TestUser();
        //userID is empty - will be generated by DB
        user.setFirstName(request.getUserFirstName());
        user.setLastName(request.getUserLastName());
        user.setLogin(request.getLogin());
        user.setPass(request.getPassword());
        user.setCreated(request.getCreated());
        user.setIdRole(request.getIdRole());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setCity(request.getCity());

        return new ResponseEntity<>(hibernateUserDao.save(user), HttpStatus.OK);
    }



    private void data(@RequestBody UserCreateRequest request, User user) {
        user.setFirstName(request.getUserFirstName());
        user.setLastName(request.getUserLastName());
        user.setUserLogin(request.getLogin());
        user.setUserPass(request.getPassword());
        user.setUserCreated(request.getCreated());
        user.setIdRole(request.getIdRole());
        user.setUserEmail(request.getEmail());
        user.setUserPhone(request.getPhone());
        user.setUserCity(request.getCity());
    }

    @ApiOperation(value = "Search user by query")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update"), //OK
            @ApiResponse(code = 400, message = "Invalid query supplied"), //Invalid request
            @ApiResponse(code = 404, message = "User was not found"), //Resourse not found
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "limit of users", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "offset", value = "start node of users", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "query", value = "search query", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> searchUsers(@ApiIgnore @ModelAttribute SearchCriteria search) {
        List<User> searchResult =
                userDao.search(
                        search.getQuery(),
                        search.getLimit(),
                        search.getOffset()
                );
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long userId) {
        userDao.delete(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}