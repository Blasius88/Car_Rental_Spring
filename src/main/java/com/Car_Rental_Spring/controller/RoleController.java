package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.role.RoleCreateRequest;
import com.Car_Rental_Spring.domain.MRoles;
import com.Car_Rental_Spring.repository.RoleDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/rest/role")
public class RoleController {

    @Autowired
    private RoleDao roleDao;

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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MRoles> getRolesById(@ApiParam("Role Path Id") @PathVariable Long id) {
        MRoles mRoles = roleDao.findById(id);
        return new ResponseEntity<>(mRoles, HttpStatus.OK);
    }


    @ApiOperation(value = "Get role from server by role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting role"),
            @ApiResponse(code = 400, message = "Invalid Role supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Role was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<MRoles> getRolesName(String str) {
        MRoles mRoles = roleDao.findRole(str);
        return new ResponseEntity<>(mRoles, HttpStatus.OK);
    }
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MRoles> createColor(@RequestBody @Valid RoleCreateRequest request) {
      MRoles mRoles = new MRoles();
        data(request, mRoles);

        MRoles savedRole = roleDao.save(mRoles);

        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }

    private void data(@RequestBody RoleCreateRequest request, MRoles mRoles) {
        mRoles.setName_roles(request.getName());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteRole(@PathVariable("id") Long roleId) {
        roleDao.delete(roleId);
        return new ResponseEntity<>(roleId, HttpStatus.OK);
    }
}
