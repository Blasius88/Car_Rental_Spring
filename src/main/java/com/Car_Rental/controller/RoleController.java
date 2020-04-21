package com.Car_Rental.controller;

import com.Car_Rental.entity.Roles;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.repository.springdata.RoleRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/admin/role")
public class RoleController {

    private final RoleRepository roleDao;

    @GetMapping("/all")
    public ResponseEntity<List<Roles>> getRoles() {
        return new ResponseEntity<>(roleDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get role from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting role"),
            @ApiResponse(code = 400, message = "Invalid Role ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Role was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Roles> getRolesById(@ApiParam("Role Path Id") @PathVariable String id) {
        Roles roles = roleDao
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(Roles.class, id));
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
