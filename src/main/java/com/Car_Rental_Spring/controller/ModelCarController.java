package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.modelCar.ModelCarCreateRequest;
import com.Car_Rental_Spring.entity.Car_Model;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.repository.springdata.ModelCarRepository;
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
@RequestMapping("/rest/modelCar")
public class ModelCarController {

    @Autowired
    private ModelCarRepository modelCarDao;

    @Autowired
    @Qualifier(value = "mvcConversionService")
    private ConversionService conversionService;

    @GetMapping("/all")
    public ResponseEntity<List<Car_Model>> getModelsCars() {
        return new ResponseEntity<>(modelCarDao.findAll(), HttpStatus.OK);
    }

    //---------------------------------------------------------------
    @ApiOperation(value = "Get Order from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Order"),
            @ApiResponse(code = 400, message = "Invalid Order ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Order was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Car_Model> getOrderById(
            @ApiParam("Order Path Id") @PathVariable String id) {
        Car_Model car_model = modelCarDao
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(Car_Model.class, id));
        return new ResponseEntity<>(car_model, HttpStatus.OK);
    }
//----------------------------------------------------------------

    @PostMapping
    @Transactional (rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Car_Model> createCarModel(
            @ModelAttribute @Valid ModelCarCreateRequest request) {
        Car_Model car_model = conversionService.convert(request, Car_Model.class);
        return new ResponseEntity<>(modelCarDao.saveAndFlush(car_model), HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteCarModelById(
            @ApiParam("Contractor Id") @PathVariable("id") Long id) {
        modelCarDao.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("update/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> updateModelCarById(
            @ModelAttribute @Valid ModelCarRepository request) {
        Car_Model convertedUser = conversionService.convert(request, Car_Model.class);
        return new ResponseEntity(modelCarDao.save(convertedUser), HttpStatus.OK);
    }
}