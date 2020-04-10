package com.Car_Rental_Spring.entity;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@ToString(exclude = { "id"})
@Entity
@Table(name = "car_brand")
public class Car_Brand  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private Double price_hour;
}