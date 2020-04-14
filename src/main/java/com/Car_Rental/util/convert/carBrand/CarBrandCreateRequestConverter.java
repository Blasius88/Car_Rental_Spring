package com.Car_Rental.util.convert.carBrand;

import com.Car_Rental.controller.requests.carBrand.CarBrandCreateRequest;
import com.Car_Rental.entity.CarBrand;
import org.springframework.stereotype.Component;

@Component
public class CarBrandCreateRequestConverter extends CarBrandRequestConverter<CarBrandCreateRequest, CarBrand> {

    @Override
    public CarBrand convert(CarBrandCreateRequest request){
        CarBrand carBrand = new CarBrand();
        return doConvert(carBrand, request);
    }
}