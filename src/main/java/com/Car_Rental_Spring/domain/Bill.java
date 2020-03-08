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
public class Bill {
    private Long id_bill;
    private Long id_order;
    private boolean status;

}