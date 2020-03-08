package com.Car_Rental_Spring.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Car_Model {
    private Long id_model;
    private String name_model;
    private int engine_capacity;
    private int date;
    private String vin;
    private Long id_color;
    private Long id_car;
}
