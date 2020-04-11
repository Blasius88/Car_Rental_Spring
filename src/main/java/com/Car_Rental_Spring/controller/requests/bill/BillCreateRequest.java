package com.Car_Rental_Spring.controller.requests.bill;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class BillCreateRequest {

    @NotNull
    private Long Id_order;

    private boolean status;

}
