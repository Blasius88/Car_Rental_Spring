package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.modelCar.ModelCarCreateRequest;
import com.Car_Rental_Spring.domain.Car_Model;
import com.Car_Rental_Spring.repository.ModelCarDao;
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
@RequestMapping("/rest/modelCar")
public class ModelCarController {

    @Autowired
    private ModelCarDao modelCarDao;

    @GetMapping
    public ResponseEntity<List<Car_Model>> getModelsCars() {
        return new ResponseEntity<>(modelCarDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Order from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Order"),
            @ApiResponse(code = 400, message = "Invalid Order ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Order was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Car_Model> getOrderById(@ApiParam("Order Path Id") @PathVariable Long id) {
        Car_Model car_model = modelCarDao.findById(id);
        return new ResponseEntity<>(car_model, HttpStatus.OK);
    }


    @ApiOperation(value = "Get Order from server by Order")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Order"),
            @ApiResponse(code = 400, message = "Invalid Order supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Order was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Car_Model> getOrderName(String str) {
        Car_Model car_model = modelCarDao.findModelName(str);
        return new ResponseEntity<>(car_model, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car_Model> createCarModel(@RequestBody @Valid ModelCarCreateRequest request) {
        Car_Model car_model = new Car_Model();
        data(request, car_model);

        Car_Model savedCar_Model = modelCarDao.save(car_model);

        return new ResponseEntity<>(savedCar_Model, HttpStatus.OK);
    }

    private void data(@RequestBody ModelCarCreateRequest request, Car_Model car_model) {
        car_model.setName_model(request.getModuleName());
        car_model.setEngine_capacity(request.getEngineCapacity());
        car_model.setDate(request.getData());
        car_model.setVin(request.getVin());
        car_model.setId_color(request.getIdColor());
        car_model.setId_car(request.getIdCar());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteCarModel(@PathVariable("id") Long carModelId) {
        modelCarDao.delete(carModelId);
        return new ResponseEntity<>(carModelId, HttpStatus.OK);
    }
}
