package com.Car_Rental_Spring.controller.messages;

import lombok.*;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorsListMessage {
    private Long errorCode;

    private List<FieldError> errors;

    private String message;

    public ErrorsListMessage(List<FieldError> errors, String message) {
        this.errors = errors;
        this.message = message;
    }
}
