package com.Car_Rental_Spring.controller.convert.carBrand;

import com.Car_Rental_Spring.controller.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.carBrand.CarBrandCreateRequest;
import com.Car_Rental_Spring.domain.Car_Brand;

public abstract class CarBrandRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Car_Brand doConvert(Car_Brand carBrand, CarBrandCreateRequest request) {
        carBrand.setName(request.getCarBrandName());
        carBrand.setPrice_hour(request.getCarBrandPriceHour());
        return carBrand;
    }
}
