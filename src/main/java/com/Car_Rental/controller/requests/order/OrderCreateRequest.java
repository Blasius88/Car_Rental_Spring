package com.Car_Rental.controller.requests.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Setter;
import lombok.Getter;

import javax.validation.constraints.NotNull;
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
    private Date rentalStart;

    @NotNull
    private String rentalStartTime;

    @NotNull
    private Date rentalEnd;

    @NotNull
    private String rentalEndTime;

}
