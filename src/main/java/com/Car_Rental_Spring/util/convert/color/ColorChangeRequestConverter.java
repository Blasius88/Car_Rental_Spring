package com.Car_Rental_Spring.util.convert.color;

import com.Car_Rental_Spring.controller.requests.color.ColorUpdateRequest;
import com.Car_Rental_Spring.entity.Color;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ColorChangeRequestConverter extends ColorRequestConverter<ColorUpdateRequest, Color> {

    @Override
    public Color convert(ColorUpdateRequest request) {
        Color color =
                ofNullable(entityManager.find(
                        Color.class, request.getColorId()))
                        .orElseThrow(() -> new EntityNotFoundException(
                                Color.class, request.getColorId()));
        return doConvert(color, request);
    }
}
