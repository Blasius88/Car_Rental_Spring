package com.Car_Rental.util.convert.worker;

import com.Car_Rental.controller.requests.worker.WorkerUserCreateRequest;
import com.Car_Rental.entity.User;
import com.Car_Rental.entity.WorkerUser;
import com.Car_Rental.exceptions.ConversionException;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.exceptions.ArgumentOfMethodNotValidException;
import com.Car_Rental.util.convert.EntityConverter;

import javax.persistence.NoResultException;

public abstract class WorkerRequestConverter<S,T> extends EntityConverter<S,T> {

    protected WorkerUser doConvert (WorkerUser workerUser, WorkerUserCreateRequest request){

        workerUser.setPercentageOfSalary(request.getPercentageOfSalary());
        return workerUser;
    }

    User findUser (Class<?> sClass, int user) {
        User users;
        try {
            users = entityManager.createQuery("select u from User u where u.userId =:name", User.class)
                    .setParameter("name", user)
                    .getSingleResult();
        } catch (NumberFormatException e) {
            throw new ConversionException(sClass, WorkerUser.class, user, new ArgumentOfMethodNotValidException(User.class, user));
        } catch (NoResultException e) {
            throw new ConversionException(sClass, WorkerUser.class, user, new EntityNotFoundException(" name = " + user, User.class));
        }
        return users;
    }
}
