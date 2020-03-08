package com.Car_Rental_Spring.controller.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorsListMessage {
    private Long errorCode;

    private List<FieldError> errors;

    private String message;
}
