package com.Car_Rental_Spring.controller.requests.bill;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BillUpdateRequest extends BillCreateRequest{
    private Long billId;
}
