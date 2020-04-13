package com.Car_Rental_Spring.controller.requests.carBrand;

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
public class CarBrandCreateRequest {

    @Size(min = 1 , max = 40)
    private String carBrandName;

}