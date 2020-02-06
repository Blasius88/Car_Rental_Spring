package com.Car_Rental_Spring.domain;

import java.util.Objects;

public class car_model extends Color {
    private Long id_model;
    private String name_model;
    private int engine_capacity;
    private int date;

    public car_model() {
    }

    public car_model(Long id_model, String name_model, int engine_capacity, int date) {
        this.id_model = id_model;
        this.name_model = name_model;
        this.engine_capacity = engine_capacity;
        this.date = date;
    }

    public car_model(Long id_color, String color, Long id_model, String name_model, int engine_capacity, int date) {
        super(id_color, color);
        this.id_model = id_model;
        this.name_model = name_model;
        this.engine_capacity = engine_capacity;
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        car_model car_model = (car_model) o;
        return engine_capacity == car_model.engine_capacity &&
                date == car_model.date &&
                Objects.equals(id_model, car_model.id_model) &&
                Objects.equals(name_model, car_model.name_model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_model, name_model, engine_capacity, date);
    }
}
