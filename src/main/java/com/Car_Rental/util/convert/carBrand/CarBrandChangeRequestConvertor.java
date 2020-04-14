package com.Car_Rental.util.convert.carBrand;

import com.Car_Rental.controller.requests.carBrand.CarBrandUpdateRequest;
import com.Car_Rental.entity.CarBrand;
import com.Car_Rental.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CarBrandChangeRequestConvertor extends CarBrandRequestConverter<CarBrandUpdateRequest, CarBrand> {

    @Override
    public CarBrand convert(CarBrandUpdateRequest request) {
        CarBrand carBrand =
                ofNullable(entityManager.find(
                        CarBrand.class, request.getCarBrandId()))
                        .orElseThrow(() -> new EntityNotFoundException(
                                CarBrand.class, request.getCarBrandId()));
        return doConvert(carBrand, request);
    }
}
