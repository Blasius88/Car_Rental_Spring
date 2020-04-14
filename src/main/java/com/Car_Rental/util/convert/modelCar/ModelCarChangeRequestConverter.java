package com.Car_Rental.util.convert.modelCar;

import com.Car_Rental.controller.requests.modelCar.ModelCarUpdateRequest;
import com.Car_Rental.entity.CarModel;
import com.Car_Rental.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ModelCarChangeRequestConverter extends ModelCarRequestConverter<ModelCarUpdateRequest, CarModel> {

    @Override
    public CarModel convert(ModelCarUpdateRequest source) {
        CarModel car_model =
                ofNullable(entityManager.find(
                        CarModel.class, source.getModelCarId()))
                        .orElseThrow(() -> new EntityNotFoundException(
                                CarModel.class, source.getModelCarId()));
        return doConvert(car_model, source);
    }
}
