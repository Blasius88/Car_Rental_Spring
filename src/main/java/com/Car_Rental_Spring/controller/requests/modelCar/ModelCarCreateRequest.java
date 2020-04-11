package com.Car_Rental_Spring.controller.requests.modelCar;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.NotEmpty;
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
public class ModelCarCreateRequest {

    @NotNull
    private String moduleName;

    @NotNull
    private Long engineCapacity;

    @NotNull
    private String data;

    @NotNull
    private String vin;

    @NotNull
    private Long idColor;

    @NotNull
    private Long idCar;
}
