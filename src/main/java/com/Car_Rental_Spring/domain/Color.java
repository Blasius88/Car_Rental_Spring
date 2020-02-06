package com.Car_Rental_Spring.domain;

import java.util.Objects;

public class Color {
    private Long id_color;
    private String color;

    public Color(){}

    public Color(Long id_color, String color) {
        this.id_color = id_color;
        this.color = color;
    }

    public Long getId_color() {
        return id_color;
    }

    public void setId_color(Long id_color) {
        this.id_color = id_color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color1 = (Color) o;
        return Objects.equals(id_color, color1.id_color) &&
                Objects.equals(color, color1.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_color, color);
    }
}
