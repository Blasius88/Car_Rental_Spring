package com.Car_Rental_Spring.controller.requests.order;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

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
    private Date rentalStart;

    @NotNull
    private String rentalStartTime;

    @NotNull
    private Date rentalEnd;

    @NotNull
    private String rentalEndTime;

    private Double orderPrice;
}
