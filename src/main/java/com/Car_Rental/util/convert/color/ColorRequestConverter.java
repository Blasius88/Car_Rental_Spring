package com.Car_Rental.util.convert.color;

import com.Car_Rental.controller.requests.color.ColorCreateRequest;
import com.Car_Rental.entity.Color;
import com.Car_Rental.util.convert.EntityConverter;

public abstract class ColorRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Color doConvert(Color color, ColorCreateRequest request) {
        color.setColorName(request.getColorName());
        return color;
    }
}
