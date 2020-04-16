package com.Car_Rental.controller;

import com.Car_Rental.controller.requests.modelCar.ModelCarCreateRequest;
import com.Car_Rental.entity.CarModel;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.repository.springdata.ModelCarRepository;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/modelCar")
public class ModelCarController {

    private final ModelCarRepository modelCarDao;

    @Autowired
    @Qualifier(value = "mvcConversionService")
    private ConversionService conversionService;

    @GetMapping("/all")
    public ResponseEntity<List<CarModel>> getModelsCars() {
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
    @GetMapping(value = "/{id}")
    public ResponseEntity<CarModel> getCarModelsById(
            @ApiParam("Order Path Id") @PathVariable String id) {
        CarModel car_model = modelCarDao
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(CarModel.class, id));
        return new ResponseEntity<>(car_model, HttpStatus.OK);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<CarModel> createCarModel(
            @RequestBody @Valid ModelCarCreateRequest request) {
        CarModel car_model = conversionService.convert(request, CarModel.class);
        return new ResponseEntity<>(modelCarDao.saveAndFlush(car_model), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteCarModelById(
            @ApiParam("Contractor Id") @PathVariable("id") Long id) {
        modelCarDao.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> updateModelCarById(
            @ModelAttribute @Valid ModelCarRepository request) {
        CarModel convertedUser = conversionService.convert(request, CarModel.class);
        return new ResponseEntity(modelCarDao.save(convertedUser), HttpStatus.OK);
    }

    @ApiOperation(value = "Uploading image to server by id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful getting image"),
            @ApiResponse(code = 400, message = "Invalid image ID supplied"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping("/photo/{idCarModel}")
    public ResponseEntity<Map<String, Object>> createCarModelPhotoPostgers(@PathVariable String idCarModel,
                                                                           @RequestBody MultipartFile multipartFile)
                                                                            throws Exception {
        modelCarDao.creteCarModelPhoto(Long.valueOf(idCarModel), multipartFile.getBytes());
        return new ResponseEntity<>(Collections.singletonMap("imageLink", "test"), HttpStatus.CREATED);
    }


}