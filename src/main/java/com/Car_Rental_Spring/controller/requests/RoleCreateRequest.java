package com.Car_Rental_Spring.controller.requests;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class RoleCreateRequest {

    private String name;
}
