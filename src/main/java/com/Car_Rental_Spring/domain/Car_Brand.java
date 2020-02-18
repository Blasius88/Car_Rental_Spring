package com.Car_Rental_Spring.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Car_Brand  {
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
