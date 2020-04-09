package com.Car_Rental_Spring.util.convert.modelCar;

import com.Car_Rental_Spring.controller.requests.modelCar.ModelCarUpdateRequest;
import com.Car_Rental_Spring.entity.Car_Model;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ModelCarChangeRequestConverter extends ModelCarRequestConverter<ModelCarUpdateRequest, Car_Model> {

    @Override
    public Car_Model convert(ModelCarUpdateRequest source) {
        Car_Model car_model =
                ofNullable(entityManager.find(
                        Car_Model.class, source.getModelCarId()))
                        .orElseThrow(() -> new EntityNotFoundException(
                                Car_Model.class, source.getModelCarId()));
        return doConvert(car_model, source);
    }
}
