package com.Car_Rental.util;

import com.Car_Rental.entity.CarModel;
import com.Car_Rental.entity.Order;
import com.Car_Rental.exceptions.DateOrTimeEnteredIncorrectly;
import com.Car_Rental.repository.springdata.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
public class ValueCalculation {
    ResultSet res = null;
    private OrderRepository orderRepository;

    public static double orderValueCalculation(Date startData, String startTime, Date endData, String endTime, CarModel carModel) throws Exception {
        try {
            double sum;
            double hour = dataParsing(startData, endData) + (timeParsing(endTime) - timeParsing(startTime));
            double price = carModel.getPriceHour();
            sum = hour * price;
            return sum;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DateOrTimeEnteredIncorrectly();
        }
    }

    public static Long dataParsing(Date dateStart, Date dateEnd) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date1 = dateFormat.parse(String.valueOf(dateStart));
            Date date2 = dateFormat.parse(String.valueOf(dateEnd));
            long milliseconds = date2.getTime() - date1.getTime();
            if (milliseconds > 0) {
                Long hours = (milliseconds / (60 * 60 * 1000));
                return hours;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static Long timeParsing(String time) {
        String[] strMas = time.split(":");
        Long[] masStartTime = new Long[strMas.length];
        for (int i = 0; i < strMas.length; i++) {
            masStartTime[i] = Long.parseLong(strMas[i]);
        }
        Long minStart = masStartTime[0] * 60 + masStartTime[1];
        return minStart;
    }

    public boolean reserveCheck(Date start, Date end, Long id) {
       try {
           Set<Order> order = orderRepository.reserveCheck(id);
           for (Order o : order) {
               if ((o.getRentalStart() == start) && (o.getRentalEnd() == end)) {
                   return false;
               }
           }
       }catch (Exception ex){
           log.error(ex.getMessage(), ex);
       }
        return true;
    }
}
