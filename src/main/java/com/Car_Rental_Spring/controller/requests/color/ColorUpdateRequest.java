package com.Car_Rental_Spring.controller.requests.color;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ColorUpdateRequest extends ColorCreateRequest{
    private Long colorId;
}
