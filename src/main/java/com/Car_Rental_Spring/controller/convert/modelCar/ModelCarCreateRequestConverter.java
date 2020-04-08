package com.Car_Rental_Spring.controller.convert.modelCar;

import com.Car_Rental_Spring.controller.requests.modelCar.ModelCarCreateRequest;
import com.Car_Rental_Spring.domain.Car_Model;
import org.springframework.stereotype.Component;

@Component
public class ModelCarCreateRequestConverter extends ModelCarRequestConverter<ModelCarCreateRequest, Car_Model>{

    @Override
    public Car_Model convert(ModelCarCreateRequest source) {
     Car_Model car_model =new Car_Model();
        return doConvert(car_model, source);
    }
}
