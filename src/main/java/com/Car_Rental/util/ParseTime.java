package com.Car_Rental.util;

public class ParseTime {
    public static Long timeParsing(String time) {
        String[] strMas = time.split(":");
        Long[] masStartTime = new Long[strMas.length];
        for (int i = 0; i < strMas.length; i++) {
            masStartTime[i] = Long.parseLong(strMas[i]);
        }
        Long minStart = 0L;
        minStart = masStartTime[0] * 60 + masStartTime[1];

        return minStart;
    }
}
