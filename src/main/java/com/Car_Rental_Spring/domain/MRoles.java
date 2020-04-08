package com.Car_Rental_Spring.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {
        "id_roles"
})
@ToString(exclude = {
        "id_roles"
})
@Entity
@Table(name = "m_roles")
public class MRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_roles;


    @Column(name = "name")
    @Size(max = 50, message = "{name_roles.maximum.size}")
    private String Name_roles;
}
