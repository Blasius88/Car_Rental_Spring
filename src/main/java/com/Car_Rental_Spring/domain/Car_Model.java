package com.Car_Rental_Spring.domain;

import java.util.Objects;

public class Car_Model {
    private Long id_model;
    private String name_model;
    private int engine_capacity;
    private int date;
    private String vin;
    private Long id_color;
    private Long id_car;


    public Car_Model(Long id_model, String name_model, int engine_capacity, int date, String vin, Long id_color, Long id_car) {
        this.id_model = id_model;
        this.name_model = name_model;
        this.engine_capacity = engine_capacity;
        this.date = date;
        this.vin = vin;
        this.id_color = id_color;
        this.id_car = id_car;
    }


    public Long getId_model() {
        return id_model;
    }

    public void setId_model(Long id_model) {
        this.id_model = id_model;
    }

    public String getName_model() {
        return name_model;
    }

    public void setName_model(String name_model) {
        this.name_model = name_model;
    }

    public int getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(int engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Long getId_color() {
        return id_color;
    }

    public void setId_color(Long id_color) {
        this.id_color = id_color;
    }

    public Long getId_car() {
        return id_car;
    }

    public void setId_car(Long id_car) {
        this.id_car = id_car;
    }

    public Car_Model() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car_Model car_model = (Car_Model) o;
        return engine_capacity == car_model.engine_capacity &&
                date == car_model.date &&
                Objects.equals(id_model, car_model.id_model) &&
                Objects.equals(name_model, car_model.name_model) &&
                Objects.equals(vin, car_model.vin) &&
                Objects.equals(id_color, car_model.id_color) &&
                Objects.equals(id_car, car_model.id_car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_model, name_model, engine_capacity, date, vin, id_color, id_car);
    }
}
