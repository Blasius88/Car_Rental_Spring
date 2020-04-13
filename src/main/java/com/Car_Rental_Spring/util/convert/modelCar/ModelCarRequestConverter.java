package com.Car_Rental_Spring.util.convert.modelCar;

import com.Car_Rental_Spring.entity.Car_Brand;
import com.Car_Rental_Spring.entity.Color;
import com.Car_Rental_Spring.exceptions.ArgumentOfMethodNotValidException;
import com.Car_Rental_Spring.exceptions.ConversionException;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.util.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.modelCar.ModelCarCreateRequest;
import com.Car_Rental_Spring.entity.Car_Model;

import javax.persistence.NoResultException;

public abstract class ModelCarRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Car_Model doConvert(Car_Model car_model, ModelCarCreateRequest request) {
        car_model.setName_model(request.getModuleName());
        car_model.setEngine_capacity(Long.valueOf(request.getEngineCapacity()));
        car_model.setDate(Long.valueOf(request.getData()));
        car_model.setVin(request.getVin());
        car_model.setPrice_hour(request.getCarBrandPriceHour());
        return car_model;


    }

    Color findColor(Class<?> sClass, int idColor) {
        Color color;
        try{
            color =entityManager.createQuery("select c from Color c where c.id =:name", Color.class)
        .setParameter("name", idColor)
        .getSingleResult();
        } catch (NumberFormatException e) {
            throw new ConversionException(sClass, Car_Model.class, idColor, new ArgumentOfMethodNotValidException(Color.class, idColor));
        } catch (NoResultException e) {
            throw new ConversionException (sClass, Car_Model.class, idColor,
                    new EntityNotFoundException(" name = " + idColor, Color.class));
        }
        return color;
    }

    Car_Brand finCarBrand (Class<?> sClass, int idCar){
        Car_Brand car_brand;
        try{
            car_brand = entityManager.createQuery("select cb from Car_Brand cb where cb.id =:name", Car_Brand.class)
                    .setParameter("name", idCar)
                    .getSingleResult();
        } catch (NumberFormatException e) {
            throw new ConversionException (sClass, Car_Model.class, idCar, new ArgumentOfMethodNotValidException (Car_Brand.class, idCar));
        } catch (NoResultException e) {
            throw new ConversionException (sClass, Car_Brand.class, idCar,
                    new EntityNotFoundException (" name = " + idCar, Car_Brand.class));
        }
        return car_brand;
    }
}
