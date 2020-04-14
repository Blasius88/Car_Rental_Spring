package com.Car_Rental.util.convert.modelCar;

import com.Car_Rental.controller.requests.modelCar.ModelCarCreateRequest;
import com.Car_Rental.entity.CarModel;
import org.springframework.stereotype.Component;

@Component
public class ModelCarCreateRequestConverter extends ModelCarRequestConverter<ModelCarCreateRequest, CarModel> {

    @Override
    public CarModel convert(ModelCarCreateRequest source) {
        CarModel car_model = new CarModel();
        car_model.setIdCar(finCarBrand(source.getClass(), Math.toIntExact(source.getIdCar())));
        car_model.setIdColor(findColor(source.getClass(), Math.toIntExact(source.getIdColor())));
        return doConvert(car_model, source);
    }
}
