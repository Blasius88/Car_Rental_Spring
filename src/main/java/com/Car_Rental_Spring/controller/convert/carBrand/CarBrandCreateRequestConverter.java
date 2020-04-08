package com.Car_Rental_Spring.controller.convert.carBrand;

import com.Car_Rental_Spring.controller.requests.carBrand.CarBrandCreateRequest;
import com.Car_Rental_Spring.domain.Car_Brand;
import org.springframework.stereotype.Component;

@Component
public class CarBrandCreateRequestConverter extends CarBrandRequestConverter<CarBrandCreateRequest, Car_Brand> {

    @Override
    public Car_Brand convert(CarBrandCreateRequest request){
        Car_Brand carBrand = new Car_Brand();
        return doConvert(carBrand, request);
    }
}