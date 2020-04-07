package com.Car_Rental_Spring.controller.convert.modelCar;

import com.Car_Rental_Spring.controller.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.modelCar.ModelCarCreateRequest;
import com.Car_Rental_Spring.domain.Car_Model;

public abstract class ModelCarRequestConverter<S,T> extends EntityConverter <S, T> {

    protected Car_Model doConvert(Car_Model car_model, ModelCarCreateRequest request)
    {
        car_model.setName_model(request.getModuleName());
        car_model.setEngine_capacity(request.getEngineCapacity());
        car_model.setDate(request.getData());
        car_model.setVin(request.getVin());
       // car_model.setId_color(request.getIdColor());
        //car_model.setId_car(request.getIdCar());

        return car_model;
    }
}
