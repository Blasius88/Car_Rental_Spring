package com.Car_Rental_Spring.controller.requests.worker;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Builder
public class WorkerUserCreateRequest {

    @NotNull
    private Long idUser;

    @NotNull
    private String nameWork;

    @NotNull
    private Double percentageOfSalary;

    @NotNull
    private Double salary;
}
