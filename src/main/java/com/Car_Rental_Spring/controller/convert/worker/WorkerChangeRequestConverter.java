package com.Car_Rental_Spring.controller.convert.worker;

import com.Car_Rental_Spring.controller.requests.worker.WorkerUserUpdateRequest;
import com.Car_Rental_Spring.domain.WorkerUser;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class WorkerChangeRequestConverter extends WorkerRequestConverter<WorkerUserUpdateRequest, WorkerUser> {

    @Override
    public WorkerUser convert(WorkerUserUpdateRequest source) {
        WorkerUser workerUser =
                ofNullable(entityManager.find(
                        WorkerUser.class, source.getWorkerId()))
                        .orElseThrow(() -> new EntityNotFoundException(
                                WorkerUser.class, source.getWorkerId()));
        return doConvert(workerUser, source);
    }
}
