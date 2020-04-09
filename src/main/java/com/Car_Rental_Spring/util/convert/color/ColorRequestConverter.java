package com.Car_Rental_Spring.util.convert.color;

import com.Car_Rental_Spring.util.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.color.ColorCreateRequest;
import com.Car_Rental_Spring.entity.Color;

public abstract class ColorRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Color doConvert(Color color, ColorCreateRequest request) {
        color.setColorName(request.getColorName());
        return color;
    }
}
