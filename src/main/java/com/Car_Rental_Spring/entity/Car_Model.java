package com.Car_Rental_Spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column (name = "engine_capacity")
    private Long engine_capacity;

    @Column(name = "data")
    private Long date;

    @Column(name ="vin")
    private String vin;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Color.class, cascade = CascadeType.ALL)
    @JoinColumn (name ="id_color")
    private Color id_color;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Car_Model.class, cascade = CascadeType.ALL)
    @JoinColumn (name = "id_car")
    private Car_Brand id_car;

    @Column(name = "image_byte")
    private byte[] imageBytes;
}
