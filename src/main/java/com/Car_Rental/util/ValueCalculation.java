package com.Car_Rental.util;

import com.Car_Rental.entity.CarModel;
import com.Car_Rental.entity.Order;
import com.Car_Rental.exceptions.DateOrTimeEnteredIncorrectly;
import com.Car_Rental.repository.springdata.ModelCarRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ValueCalculation {

    private ModelCarRepository modelCarRepository;

    public static double orderValueCalculation(Date startData, String startTime, Date endData, String endTime, CarModel carModel) {
        try {
            double sum;
            double hour = dataParsing(startData, endData) + (timeParsing(endTime) - timeParsing(startTime));
            double price = carModel.getPriceHour();
            sum = hour * price;
            return sum;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static Long dataParsing(Date dateStart, Date dateEnd) {
        Long hours = 0L;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date1 = dateFormat.parse(String.valueOf(dateStart));
            Date date2 = dateFormat.parse(String.valueOf(dateEnd));
            long milliseconds = date2.getTime() - date1.getTime();
            if (milliseconds > 0) {
                hours = (milliseconds / (60 * 60 * 1000));
                return hours;
            } else throw new DateOrTimeEnteredIncorrectly();
        } catch (Exception e) {
            return hours;
        }
    }

    public static Long timeParsing(String time) {
        String[] strMas = time.split(":");
        Long[] masStartTime = new Long[strMas.length];
        for (int i = 0; i < strMas.length; i++) {
            masStartTime[i] = Long.parseLong(strMas[i]);
        }
        Long minStart;
        minStart = masStartTime[0] * 60 + masStartTime[1];

        return minStart;
    }

    public static boolean reserveCheck(Date start, Date end, Long id) {
        Collection<Order> orderList = new ArrayList<>();
        for (Order o : orderList) {
            if (o.getOrderCarId().getIdModel() == id)
                if ((o.getRentalStart() == start) && (o.getRentalEnd() == end)) {
                    return false;
                }
        }
        return true;
    }
}
