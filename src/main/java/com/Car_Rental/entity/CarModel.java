package com.Car_Rental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "model_car")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idModel;

    @Column(name = "modul_name")
    private String nameModel;

    @Column (name = "engine_capacity")
    private Long engineCapacity;

    @Column(name = "data")
    private Long date;

    @Column(name ="vin")
    private String vin;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Color.class, cascade = CascadeType.ALL)
    @JoinColumn (name ="id_color")
    private Color idColor;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CarBrand.class, cascade = CascadeType.ALL)
    @JoinColumn (name = "id_car")
    private CarBrand idCar;

    @Column(name = "image_byte")
    private byte[] imageBytes;

    @Column(name ="price_hour")
    private Double priceHour;

}
