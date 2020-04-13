package com.Car_Rental_Spring.controller.requests.order;

import lombok.*;

import javax.validation.constraints.NotEmpty;
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

    @NotNull
    @NotEmpty
    private String rentalStart;

    @NotNull
    @NotEmpty
    private String rentalStartTime;

    @NotNull
    @NotEmpty
    private String rentalEnd;

    @NotNull
    @NotEmpty
    private String rentalEndTime;

    private Double orderPrice;
}
