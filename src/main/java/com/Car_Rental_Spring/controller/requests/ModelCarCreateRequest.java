package com.Car_Rental_Spring.controller.requests;

import lombok.*;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class ModelCarCreateRequest {

    @Size(min =1)
    private String moduleName;

    @Size(min =1)
    private int engineCapacity;

    @Size(min =1)
    private int data;

    @Size(min =1)
    private String vin;

    @Size(min =1)
    private Long idColor;

    @Size(min =1)
    private Long idCar;

}
