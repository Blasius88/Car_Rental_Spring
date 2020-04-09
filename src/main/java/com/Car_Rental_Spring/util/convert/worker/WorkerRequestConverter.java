package com.Car_Rental_Spring.util.convert.worker;

import com.Car_Rental_Spring.util.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.worker.WorkerUserCreateRequest;
import com.Car_Rental_Spring.entity.WorkerUser;

public abstract class WorkerRequestConverter<S,T> extends EntityConverter<S,T> {

    protected WorkerUser doConvert (WorkerUser workerUser, WorkerUserCreateRequest request){

        workerUser.setNameWork(request.getNameWork());
      //  workerUser.setId_user(request.getIdUser());
        workerUser.setPercentage_of_salary(request.getPercentageOfSalary());
        workerUser.setSalary(request.getSalary());

        return workerUser;
    }
}
