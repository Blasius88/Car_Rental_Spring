package com.Car_Rental_Spring.util.convert.order;

import com.Car_Rental_Spring.entity.Car_Model;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.entity.WorkerUser;
import com.Car_Rental_Spring.exceptions.ArgumentOfMethodNotValidException;
import com.Car_Rental_Spring.exceptions.ConversionException;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.util.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.entity.Order;

import javax.persistence.NoResultException;

public abstract class OrderRequestConverter<S, T> extends EntityConverter<S, T> {
    protected Order doConvert(Order order, OrderCreateRequest request) {
        //order.setOrderWorkerId(request.getIdWorker());
        order.setRentalStart(request.getRentalStart());
        order.setRentalEnd(request.getRentalEnd());
        return order;
    }


    User findUser(Class<?> sClass, int idUser) {
        User users;
        try {
            users = entityManager.createQuery("select r from User r where r.userId =:name", User.class)
                    .setParameter("name", idUser)
                    .getSingleResult();
        } catch (NumberFormatException e) {
            throw new ConversionException(sClass, Order.class, idUser, new ArgumentOfMethodNotValidException(User.class, idUser));
        } catch (NoResultException e) {
            throw new ConversionException(sClass, Order.class, idUser,
                    new EntityNotFoundException(" name = " + idUser, User.class));
        }
        return users;
    }

    Car_Model findCar(Class<?> sClass, int idCar) {
        Car_Model car_model;
        try {
            car_model = entityManager.createQuery("select cm from Car_Model cm where cm.id_car=:name", Car_Model.class)
                    .setParameter("name", idCar)
                    .getSingleResult();
        } catch (NumberFormatException e) {
            throw new ConversionException(sClass, Car_Model.class, idCar, new ArgumentOfMethodNotValidException(Car_Model.class, idCar));
        } catch (NoResultException e) {
            throw new ConversionException(sClass, Car_Model.class, idCar,
                    new EntityNotFoundException(" name = " + idCar, Car_Model.class));
        }
        return car_model;
    }

    WorkerUser findWorker(Class<?> sClass, int idWorker) {
        WorkerUser workerUser;
        try{
            workerUser = entityManager.createQuery("select wu from WorkerUser wu where wu.id_worker=:name", WorkerUser.class)
                    .setParameter("name", idWorker)
                    .getSingleResult();
        } catch (NumberFormatException e) {
            throw new ConversionException(sClass, WorkerUser.class, idWorker, new ArgumentOfMethodNotValidException(WorkerUser.class, idWorker));
        } catch (NoResultException e) {
            throw new ConversionException(sClass, WorkerUser.class, idWorker,
                    new EntityNotFoundException(" name = " + idWorker, WorkerUser.class));
        }
        return workerUser;
    }
}
