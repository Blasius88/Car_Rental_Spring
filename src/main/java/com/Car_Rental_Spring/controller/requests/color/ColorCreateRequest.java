package com.Car_Rental_Spring.controller.requests.color;


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
public class ColorCreateRequest {
    @Size(min = 1, max = 100)
    private String colorName;
}
