package com.Car_Rental_Spring.controller.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorMessage {
    private Long errorCode;

    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }
}
