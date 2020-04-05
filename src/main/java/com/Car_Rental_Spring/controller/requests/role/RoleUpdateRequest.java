package com.Car_Rental_Spring.controller.requests.role;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateRequest extends RoleCreateRequest{
    private Long roleId;
}
