package com.Car_Rental_Spring.domain;

import lombok.*;

import javax.persistence.*;


@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode()
@ToString()
@Entity
@Table(name = "model_car")
public class Car_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_model;

    @Column(name = "modul_name")
    private String name_model;

    @Column
    private int engine_capacity;

    @Column
    private int date;

    @Column
    private String vin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Color id_color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Car_Brand id_car;
}
