package com.Car_Rental.util.convert.carBrand;

import com.Car_Rental.controller.requests.carBrand.CarBrandCreateRequest;
import com.Car_Rental.entity.CarBrand;
import com.Car_Rental.util.convert.EntityConverter;

public abstract class CarBrandRequestConverter<S, T> extends EntityConverter<S, T> {

    protected CarBrand doConvert(CarBrand carBrand, CarBrandCreateRequest request) {
        carBrand.setName(request.getCarBrandName());
        return carBrand;
    }
}
