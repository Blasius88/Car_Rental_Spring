package com.Car_Rental_Spring.controller.requests.order;

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
public class OrderCreateRequest {

    @Size(min = 1)
    private Long idUser;

    @Size(min = 1)
    private Long idCar;

    @Size(min = 1)
    private Long idWorker;

    @Size(min = 1, max = 10)
    private String rentalStart;

    @Size(min = 1, max = 10)
    private String rentalEnd;
}
