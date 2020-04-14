package com.Car_Rental.exceptions;

import java.io.PrintStream;

public class DateOrTimeEnteredIncorrectly extends Exception {

    @Override
    public void printStackTrace(PrintStream e){
        System.err.println("data entry error");
    }
}
