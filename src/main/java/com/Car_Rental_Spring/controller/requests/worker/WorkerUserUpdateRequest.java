package com.Car_Rental_Spring.controller.requests.worker;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WorkerUserUpdateRequest extends WorkerUserCreateRequest {
    private int workerId;
}

