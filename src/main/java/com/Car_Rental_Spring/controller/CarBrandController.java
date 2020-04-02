package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.carBrand.CarBrandCreateRequest;
import com.Car_Rental_Spring.domain.Car_Brand;
import com.Car_Rental_Spring.repository.Car_BrandDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/CarBrand")
public class CarBrandController {

    @Autowired
    private Car_BrandDao car_BrandDao;

    @GetMapping
    public ResponseEntity<List<Car_Brand>> getCarBrands() {
        return new ResponseEntity<>(car_BrandDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get CarBrand from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting CarBrand"),
            @ApiResponse(code = 400, message = "Invalid CarBrand ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "CarBrand was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Car_Brand> getCarBrandById(@ApiParam("CarBrand Path Id") @PathVariable Long id) {
        Car_Brand carBrand = car_BrandDao.findById(id);
        return new ResponseEntity<>(carBrand, HttpStatus.OK);
    }


    @ApiOperation(value = "Get CarBrand from server by CarBrand")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting CarBrand"),
            @ApiResponse(code = 400, message = "Invalid CarBrand supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "CarBrand was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/searchName", method = RequestMethod.GET)
    public ResponseEntity<Car_Brand> getCarBrandName(String str) {
        Car_Brand carBrand = car_BrandDao.findCarBrand(str);
        return new ResponseEntity<>(carBrand, HttpStatus.OK);
    }
    
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car_Brand> createCarBrand(@RequestBody @Valid CarBrandCreateRequest request) {
        Car_Brand carBrand = new Car_Brand();
        data(request, carBrand);

        Car_Brand savedCarBrand = car_BrandDao.save(carBrand);

        return new ResponseEntity<>(savedCarBrand, HttpStatus.OK);
    }

    private void data(@RequestBody CarBrandCreateRequest request, Car_Brand carBrand) {
        carBrand.setName(request.getCarBrandName());
        carBrand.setPrice_hour(request.getCarBrandPriceHour());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteCarBrand(@PathVariable("id") Long carBrandId) {
        car_BrandDao.delete(carBrandId);
        return new ResponseEntity<>(carBrandId, HttpStatus.OK);
    }
}