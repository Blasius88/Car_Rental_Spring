package com.Car_Rental_Spring.domain;


import lombok.*;

import javax.persistence.*;


@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@ToString(exclude = {"id"})
@Entity
@Table(name = "color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column (name = "color")
    private String colorName;
}
