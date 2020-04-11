package com.Car_Rental_Spring.util.convert.worker;

import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.exceptions.ArgumentOfMethodNotValidException;
import com.Car_Rental_Spring.exceptions.ConversionException;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.util.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.worker.WorkerUserCreateRequest;
import com.Car_Rental_Spring.entity.WorkerUser;

import javax.persistence.NoResultException;

public abstract class WorkerRequestConverter<S,T> extends EntityConverter<S,T> {

    protected WorkerUser doConvert (WorkerUser workerUser, WorkerUserCreateRequest request){

        workerUser.setNameWork(request.getNameWork());
        workerUser.setPercentage_of_salary(request.getPercentageOfSalary());
        workerUser.setSalary(request.getSalary());
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
