package com.example.demo.application.exceptions;

public class UserAlreadyExistsException extends ApplicationException {
    public UserAlreadyExistsException() {
        super("User already exists", "USER_ALREADY_EXISTS", 400);
    }

    public UserAlreadyExistsException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
