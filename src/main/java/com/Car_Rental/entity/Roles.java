package com.Car_Rental.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "m_roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_roles;


    @Column(name = "name")
    @Size(max = 50, message = "{name_roles.maximum.size}")
    private String nameRoles;
}
