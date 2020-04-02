package com.Car_Rental_Spring.controller.requests.carBrand;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CarBrandUpdateRequest extends CarBrandCreateRequest{

    private Long carBrandId;
}
