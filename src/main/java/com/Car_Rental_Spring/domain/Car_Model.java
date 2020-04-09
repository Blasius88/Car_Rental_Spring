package com.Car_Rental_Spring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;


@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id_model", "id_color", "id_car"})
@ToString(exclude = {"id_model", "id_color", "id_car"})
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

    @Column(name = "data")
    private int date;

    @Column
    private String vin;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn (name ="id_color")
    private Color id_color;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "id_car")
    private Car_Brand id_car;
}
