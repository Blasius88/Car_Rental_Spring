package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.domain.MRoles;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.repository.springdata.RoleRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/rest/role")
public class RoleController {

    private final RoleRepository roleDao;

    @GetMapping
    public ResponseEntity<List<MRoles>> getRoles() {
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
    public ResponseEntity<MRoles> getRolesById(@ApiParam("Role Path Id") @PathVariable String id) {
        MRoles mRoles = roleDao
                .findById(Long.valueOf(id))
                .orElseThrow(()-> new EntityNotFoundException(MRoles.class, id));
        return new ResponseEntity<>(mRoles, HttpStatus.OK);
    }
}
