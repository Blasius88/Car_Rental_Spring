package com.Car_Rental_Spring.util.convert.color;

import com.Car_Rental_Spring.controller.requests.color.ColorCreateRequest;
import com.Car_Rental_Spring.entity.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorCreateRequestConverter extends ColorRequestConverter<ColorCreateRequest, Color> {
    @Override
    public Color convert(ColorCreateRequest source) {
       Color color = new Color();
       return doConvert(color, source);
    }
}
