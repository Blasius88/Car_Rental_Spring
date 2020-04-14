package com.Car_Rental.controller.requests.modelCar;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class ModelCarUpdateRequest extends ModelCarCreateRequest {
    private Long modelCarId;
}
