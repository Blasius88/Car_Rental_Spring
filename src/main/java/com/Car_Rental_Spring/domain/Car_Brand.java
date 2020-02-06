package com.Car_Rental_Spring.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Car_Brand extends car_model {
    private Long id;
    private String name;
    private Double price_hour;

    public Car_Brand() {
    }

    public Car_Brand(Long id, String name, Double price_hour) {
        this.id = id;
        this.name = name;
        this.price_hour = price_hour;
    }

    public Car_Brand(Long id_model, String name_model, int engine_capacity, int date, Long id, String name, Double price_hour) {
        super(id_model, name_model, engine_capacity, date);
        this.id = id;
        this.name = name;
        this.price_hour = price_hour;
    }

    public Car_Brand(Long id_color, String color, Long id_model, String name_model, int engine_capacity, int date, Long id, String name, Double price_hour) {
        super(id_color, color, id_model, name_model, engine_capacity, date);
        this.id = id;
        this.name = name;
        this.price_hour = price_hour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice_hour() {
        return price_hour;
    }

    public void setPrice_hour(Double price_hour) {
        this.price_hour = price_hour;
    }

    public Car_Brand(Long id_model, String name_model, int engine_capacity, int date) {
        super(id_model, name_model, engine_capacity, date);
    }

    public Car_Brand(Long id_color, String color, Long id_model, String name_model, int engine_capacity, int date) {
        super(id_color, color, id_model, name_model, engine_capacity, date);
    }

    @Override
    public Long getId_model() {
        return super.getId_model();
    }

    @Override
    public void setId_model(Long id_model) {
        super.setId_model(id_model);
    }

    @Override
    public String getName_model() {
        return super.getName_model();
    }

    @Override
    public void setName_model(String name_model) {
        super.setName_model(name_model);
    }

    @Override
    public int getEngine_capacity() {
        return super.getEngine_capacity();
    }

    @Override
    public void setEngine_capacity(int engine_capacity) {
        super.setEngine_capacity(engine_capacity);
    }

    @Override
    public int getDate() {
        return super.getDate();
    }

    @Override
    public void setDate(int date) {
        super.setDate(date);
    }

    @Override
    public Long getId_color() {
        return super.getId_color();
    }

    @Override
    public void setId_color(Long id_color) {
        super.setId_color(id_color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(String color) {
        super.setColor(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car_Brand car_brand = (Car_Brand) o;
        return Objects.equals(id, car_brand.id) &&
                Objects.equals(name, car_brand.name) &&
                Objects.equals(price_hour, car_brand.price_hour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, price_hour);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
