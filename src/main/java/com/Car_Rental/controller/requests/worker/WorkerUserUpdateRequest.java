package com.Car_Rental.controller.requests.worker;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WorkerUserUpdateRequest extends WorkerUserCreateRequest {
    private Long workerId;
}

