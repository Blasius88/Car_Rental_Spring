package com.Car_Rental_Spring.util.convert.carBrand;

import com.Car_Rental_Spring.util.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.carBrand.CarBrandCreateRequest;
import com.Car_Rental_Spring.entity.Car_Brand;

public abstract class CarBrandRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Car_Brand doConvert(Car_Brand carBrand, CarBrandCreateRequest request) {
        carBrand.setName(request.getCarBrandName());
        carBrand.setPrice_hour(request.getCarBrandPriceHour());
        return carBrand;
    }
}
