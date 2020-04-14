package com.Car_Rental.controller.requests.modelCar;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;
import lombok.AccessLevel;

import javax.validation.constraints.NotNull;

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

    @NotNull
    private byte[] imageBytes;

    @NotNull
    private Double carBrandPriceHour;
}
