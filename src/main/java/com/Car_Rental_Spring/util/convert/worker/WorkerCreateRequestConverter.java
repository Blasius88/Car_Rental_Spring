package com.Car_Rental_Spring.util.convert.worker;

import com.Car_Rental_Spring.controller.requests.worker.WorkerUserCreateRequest;
import com.Car_Rental_Spring.entity.WorkerUser;
import org.springframework.stereotype.Component;

@Component
public class WorkerCreateRequestConverter extends WorkerRequestConverter<WorkerUserCreateRequest, WorkerUser> {

    @Override
    public WorkerUser convert(WorkerUserCreateRequest source) {
        WorkerUser workerUser = new WorkerUser();
        return doConvert(workerUser, source);
    }
}
