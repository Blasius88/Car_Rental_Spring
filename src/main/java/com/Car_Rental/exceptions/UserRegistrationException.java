package com.Car_Rental.exceptions;

public class UserRegistrationException extends RuntimeException {
    private static final String MESSAGE_FIELD_TEMPLATE = "User with %s=%s already exists";
    private static final String MESSAGE_CONFIRM_TEMPLATE = "%s with login = %s exists but not confirmed";


    public UserRegistrationException() {
        super();
    }

    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException(String entityName, String entityField, String entityFieldSearch) {
        super(String.format(MESSAGE_FIELD_TEMPLATE, entityName, entityField, entityFieldSearch));
    }

    public UserRegistrationException(String entity, String entityLogin) {
        super(String.format(MESSAGE_CONFIRM_TEMPLATE, entity, entityLogin));
    }


}
