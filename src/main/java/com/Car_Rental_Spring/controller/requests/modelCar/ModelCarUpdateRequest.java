package com.Car_Rental_Spring.controller.requests.modelCar;

import lombok.*;

@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class ModelCarUpdateRequest extends ModelCarCreateRequest {
    private Long modelCarId;
}
