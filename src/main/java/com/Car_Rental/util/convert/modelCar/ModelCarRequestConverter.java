package com.Car_Rental.util.convert.modelCar;

import com.Car_Rental.controller.requests.modelCar.ModelCarCreateRequest;
import com.Car_Rental.entity.CarBrand;
import com.Car_Rental.entity.CarModel;
import com.Car_Rental.entity.Color;
import com.Car_Rental.exceptions.ConversionException;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.exceptions.ArgumentOfMethodNotValidException;
import com.Car_Rental.util.convert.EntityConverter;

import javax.persistence.NoResultException;

public abstract class ModelCarRequestConverter<S, T> extends EntityConverter<S, T> {

    protected CarModel doConvert(CarModel car_model, ModelCarCreateRequest request) {
        car_model.setNameModel(request.getModuleName());
        car_model.setEngineCapacity(Long.valueOf(request.getEngineCapacity()));
        car_model.setDate(Long.valueOf(request.getData()));
        car_model.setVin(request.getVin());
        car_model.setPriceHour(request.getCarBrandPriceHour());
        return car_model;


    }

    Color findColor(Class<?> sClass, int idColor) {
        Color color;
        try{
            color =entityManager.createQuery("select c from Color c where c.id =:name", Color.class)
        .setParameter("name", idColor)
        .getSingleResult();
        } catch (NumberFormatException e) {
            throw new ConversionException(sClass, CarModel.class, idColor, new ArgumentOfMethodNotValidException(Color.class, idColor));
        } catch (NoResultException e) {
            throw new ConversionException (sClass, CarModel.class, idColor,
                    new EntityNotFoundException(" name = " + idColor, Color.class));
        }
        return color;
    }

    CarBrand finCarBrand (Class<?> sClass, int idCar){
        CarBrand car_brand;
        try{
            car_brand = entityManager.createQuery("select cb from CarBrand cb where cb.id =:name", CarBrand.class)
                    .setParameter("name", idCar)
                    .getSingleResult();
        } catch (NumberFormatException e) {
            throw new ConversionException (sClass, CarModel.class, idCar, new ArgumentOfMethodNotValidException (CarBrand.class, idCar));
        } catch (NoResultException e) {
            throw new ConversionException (sClass, CarBrand.class, idCar,
                    new EntityNotFoundException (" name = " + idCar, CarBrand.class));
        }
        return car_brand;
    }
}
