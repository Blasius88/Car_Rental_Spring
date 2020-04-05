package com.Car_Rental_Spring.controller.convert.carBrand;

import com.Car_Rental_Spring.controller.requests.carBrand.CarBrandUpdateRequest;
import com.Car_Rental_Spring.domain.Car_Brand;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CarBrandChangeRequestConvertor extends CarBrandRequestConverter<CarBrandUpdateRequest, Car_Brand> {

    @Override
    public Car_Brand convert(CarBrandUpdateRequest request) {
        Car_Brand carBrand =
                ofNullable(entityManager.find(
                        Car_Brand.class, request.getCarBrandId()))
                        .orElseThrow(() -> new EntityNotFoundException(
                                Car_Brand.class, request.getCarBrandId()));
        return doConvert(carBrand, request);
    }
}
