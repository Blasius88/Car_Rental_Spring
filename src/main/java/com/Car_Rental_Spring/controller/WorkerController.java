package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.worker.WorkerUserCreateRequest;
import com.Car_Rental_Spring.domain.WorkerUser;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.repository.springdata.WorkerRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
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

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/worker")
public class WorkerController {

    @Autowired
    private WorkerRepository workerUserDao;

    @Autowired
    @Qualifier(value = "mvcConversionService")
    private ConversionService conversionService;

    @ApiOperation(value = "Get all worker from server")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting data"),
            @ApiResponse(code = 400, message = "Something wrong"),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Worker not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping ("/all")
    @ResponseStatus(HttpStatus.OK)
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
    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerUser> getWorkerById(
            @ApiParam("worker Path Id") @PathVariable String id) {
        WorkerUser workerUser = workerUserDao
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(WorkerUser.class, id));
        return new ResponseEntity<>(workerUser, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WorkerUser> createWorker
            (@ModelAttribute @Valid WorkerUserCreateRequest request) {
        WorkerUser workerUser = conversionService.convert(request, WorkerUser.class);
        return new ResponseEntity<>(workerUserDao.saveAndFlush(workerUser), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteWorkerUser(
            @PathVariable("id") Long workerUserId) {
        workerUserDao.deleteById(workerUserId);
        return new ResponseEntity<>(workerUserId, HttpStatus.OK);
    }
}
