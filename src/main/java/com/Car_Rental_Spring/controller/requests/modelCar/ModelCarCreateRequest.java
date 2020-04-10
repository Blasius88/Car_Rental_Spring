package com.Car_Rental_Spring.controller.requests.modelCar;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class ModelCarCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min =1)
    private String moduleName;

    @NotNull
    @NotEmpty
    private Long engineCapacity;

    @NotNull
    @NotEmpty
    private String data;

    @NotNull
    @NotEmpty
    private String vin;

    @NotNull
    @NotEmpty
    private Long idColor;

    @NotNull
    @NotEmpty
    private Long idCar;
}
