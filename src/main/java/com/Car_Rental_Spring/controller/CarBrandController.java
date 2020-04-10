package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.carBrand.CarBrandCreateRequest;
import com.Car_Rental_Spring.entity.Car_Brand;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.repository.springdata.CarBrandRepository;
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
@RequestMapping("/rest/CarBrand")
public class CarBrandController {

    private final CarBrandRepository carBrandRepository;


    @Autowired
    @Qualifier(value = "mvcConversionService")
    private ConversionService conversionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Car_Brand>> getCarBrands() {
        return new ResponseEntity<>(carBrandRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get CarBrand from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting CarBrand"),
            @ApiResponse(code = 400, message = "Invalid CarBrand ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "CarBrand was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Car_Brand> getCarBrandById(
            @ApiParam("CarBrand Path Id") @PathVariable String id) {
        Car_Brand carBrand = carBrandRepository
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(Car_Brand.class, id));
        return new ResponseEntity<>(carBrand, HttpStatus.OK);
    }

    //--------------------------------------
    @ApiOperation(value = "Create car brand")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful creating"),
            @ApiResponse(code = 400, message = "Invalid car brand properties supplied"),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Something wrong"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<Car_Brand> createCarBrand(
            @RequestBody @Valid CarBrandCreateRequest request) {
        Car_Brand carBrand = conversionService.convert(request, Car_Brand.class);
        return new ResponseEntity<>(carBrandRepository.saveAndFlush(carBrand), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete user from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleting user"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @DeleteMapping("delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteCarBrand( @ApiParam("User Path Id")
            @PathVariable("id") Long id) {
        carBrandRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}