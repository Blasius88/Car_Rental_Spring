package com.Car_Rental_Spring.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class WorkerUser {
    private Long id_worker;
    private Long id_user;
    private double percentage_of_salary;
    private double salary;
}