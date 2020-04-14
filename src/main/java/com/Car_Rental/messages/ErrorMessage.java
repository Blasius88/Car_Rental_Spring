package com.Car_Rental.messages;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private Long errorCode;

    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

}