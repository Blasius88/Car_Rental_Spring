package com.Car_Rental.util.convert.color;

import com.Car_Rental.controller.requests.color.ColorCreateRequest;
import com.Car_Rental.entity.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorCreateRequestConverter extends ColorRequestConverter<ColorCreateRequest, Color> {
    @Override
    public Color convert(ColorCreateRequest source) {
       Color color = new Color();
       return doConvert(color, source);
    }
}
