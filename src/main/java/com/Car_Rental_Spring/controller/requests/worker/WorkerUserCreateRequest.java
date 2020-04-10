package com.Car_Rental_Spring.controller.requests.worker;

import lombok.*;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class WorkerUserCreateRequest {

    @Size(min = 1)
    private int idUser;

    @Size (min = 1)
    private String nameWork;

    @Size(min = 1)
    private Double percentageOfSalary;

    private Double salary;
}
