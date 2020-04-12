package com.Car_Rental_Spring.controller.requests.order;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class OrderCreateRequest {
    @NotNull
    private Long idUser;

    @NotNull
    private Long idCar;

    @NotNull
    private Long idWorker;

    @Size(min = 1, max = 10)
    private Timestamp rentalStart;

    @Size(min = 1, max = 10)
    private Timestamp rentalEnd;

    private Double orderPrice;
}
