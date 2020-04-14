package com.Car_Rental.entity;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(
        exclude = {
                "id"
        })
@ToString(
        exclude = {
                "id"
        })
@Entity
@Table(name = "car_brand")
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;
}