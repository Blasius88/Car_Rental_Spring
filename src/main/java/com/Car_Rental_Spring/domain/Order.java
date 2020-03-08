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
public class Order {
    private Long orderId;
    private Long orderUserId;
    private Long orderCarId;
    private Long orderWorkerId;
    private  String rentalStart;
    private String rentalEnd;
}
