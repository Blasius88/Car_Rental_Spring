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
public class MRoles {
    private Long id_roles;
    private String Name_roles;
}
