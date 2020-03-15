package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.WorkerUserCreateRequest;
import com.Car_Rental_Spring.domain.WorkerUser;
import com.Car_Rental_Spring.repository.WorkerUserDao;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/rest/worker")
public class WorkerController {

    @Autowired
    private WorkerUserDao workerUserDao;

    @GetMapping
    public ResponseEntity<List<WorkerUser>> getWorkers() {
        return new ResponseEntity<>(workerUserDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get worker from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting color"),
            @ApiResponse(code = 400, message = "Invalid worker ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Color was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<WorkerUser> getColorById(@ApiParam("worker Path Id") @PathVariable Long id) {
        WorkerUser workerUser = workerUserDao.findById(id);
        return new ResponseEntity<>(workerUser, HttpStatus.OK);
    }


    @ApiOperation(value = "Get worker from server by work")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting work"),
            @ApiResponse(code = 400, message = "Invalid work supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "work was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<WorkerUser> getWorkName(String str) {
        WorkerUser workerUser= workerUserDao.findWorkName(str);
        return new ResponseEntity<>(workerUser, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WorkerUser> createColor(@RequestBody @Valid WorkerUserCreateRequest request) {
        WorkerUser workerUser = new WorkerUser();
        data(request, workerUser);

        WorkerUser savedWorkerUser = workerUserDao.save(workerUser);

        return new ResponseEntity<>(savedWorkerUser, HttpStatus.OK);
    }

    @ApiOperation(value = "Update color by colorID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful color update"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    private void data(@RequestBody WorkerUserCreateRequest request, WorkerUser workerUser) {
        workerUser.setNameWork(request.getNameWork());
        workerUser.setId_user(request.getIdUser());
        workerUser.setPercentage_of_salary(request.getPercentageOfSalary());
        workerUser.setSalary(request.getSalary());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteWorkerUser(@PathVariable("id") Long workerUserId) {
        workerUserDao.delete(workerUserId);
        return new ResponseEntity<>(workerUserId, HttpStatus.OK);
    }
}

