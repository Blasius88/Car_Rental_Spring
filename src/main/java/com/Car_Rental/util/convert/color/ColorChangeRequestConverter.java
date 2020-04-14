package com.Car_Rental.util.convert.color;

import com.Car_Rental.controller.requests.color.ColorUpdateRequest;
import com.Car_Rental.entity.Color;
import com.Car_Rental.exceptions.EntityNotFoundException;
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
