package com.Car_Rental_Spring.util.convert.modelCar;

import com.Car_Rental_Spring.util.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.modelCar.ModelCarCreateRequest;
import com.Car_Rental_Spring.entity.Car_Model;

public abstract class ModelCarRequestConverter<S,T> extends EntityConverter <S, T> {

    protected Car_Model doConvert(Car_Model car_model, ModelCarCreateRequest request)
    {
        car_model.setName_model(request.getModuleName());
        car_model.setEngine_capacity(Long.valueOf(request.getEngineCapacity()));
        car_model.setDate(Long.valueOf(request.getData()));
        car_model.setVin(request.getVin());
        return car_model;
    }

}
