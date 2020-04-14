package com.Car_Rental.util;

import com.Car_Rental.exceptions.DateOrTimeEnteredIncorrectly;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {
    public static Long dataParsing(Date dateStart, Date dateEnd) {
        Long hours = 0L;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date1 =  dateFormat.parse(String.valueOf(dateStart));
            Date date2 =  dateFormat.parse(String.valueOf(dateEnd));
            long milliseconds = date2.getTime() - date1.getTime();
            if (milliseconds > 0) {
                hours = (milliseconds / (60 * 60 * 1000));
                return hours;
            } else throw new DateOrTimeEnteredIncorrectly();
        } catch (Exception e) {
            return hours;
        }
    }
}
