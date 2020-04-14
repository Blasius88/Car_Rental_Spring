package com.Car_Rental.util.convert.worker;

import com.Car_Rental.controller.requests.worker.WorkerUserCreateRequest;
import com.Car_Rental.entity.WorkerUser;
import org.springframework.stereotype.Component;

@Component
public class WorkerCreateRequestConverter extends WorkerRequestConverter<WorkerUserCreateRequest, WorkerUser> {

    @Override
    public WorkerUser convert(WorkerUserCreateRequest source) {
        WorkerUser workerUser = new WorkerUser();
        workerUser.setIdUser(findUser(source.getClass(), Math.toIntExact(source.getIdUser())));

        return doConvert(workerUser, source);
    }
}
