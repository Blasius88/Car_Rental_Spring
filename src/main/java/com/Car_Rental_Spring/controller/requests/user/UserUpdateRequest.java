package com.Car_Rental_Spring.controller.requests.user;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest extends UserCreateRequest {
    private int userId;
}