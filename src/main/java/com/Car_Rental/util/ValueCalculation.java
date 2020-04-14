package com.Car_Rental.util;

import com.Car_Rental.entity.CarModel;

import java.util.Date;

public class ValueCalculation {
    public static double orderValueCalculation(Date startData, String startTime, Date endData, String endTime, CarModel carModel) {
        try {
            double sum;
            double hour = ParseDate.dataParsing(startData, endData) + (ParseTime.timeParsing(endTime) - ParseTime.timeParsing(startTime));
            double price = carModel.getPriceHour();
            sum = hour * price;
            return sum;
        } catch (Exception e) {
            return 0.0;
        }
    }

}
