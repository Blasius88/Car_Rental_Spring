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
public class Color {
    private Long id;
    private String color;
}
