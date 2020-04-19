package com.Car_Rental.controller;

import com.Car_Rental.controller.requests.carBrand.CarBrandCreateRequest;
import com.Car_Rental.entity.CarBrand;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.repository.springdata.CarBrandRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/CarBrand")
public class CarBrandController {

    private final CarBrandRepository carBrandRepository;

    @Qualifier(value = "mvcConversionService")
    private ConversionService conversionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CarBrand>> getCarBrands() {
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
    public ResponseEntity<CarBrand> getCarBrandById(
            @ApiParam("CarBrand Path Id") @PathVariable String id) {
        CarBrand carBrand = carBrandRepository
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(CarBrand.class, id));
        return new ResponseEntity<>(carBrand, HttpStatus.OK);
    }

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
    public ResponseEntity<CarBrand> createCarBrand(
            @RequestBody @Valid CarBrandCreateRequest request) {
        CarBrand carBrand = conversionService.convert(request, CarBrand.class);
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
    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCarBrand(@ApiParam("User Path Id")
                                               @PathVariable("id") String id) {
        carBrandRepository.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}