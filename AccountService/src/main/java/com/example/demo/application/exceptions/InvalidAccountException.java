package com.example.demo.application.exceptions;

public class InvalidAccountException extends ApplicationException {
    public InvalidAccountException() {
        super("Invalid account type or initial balance.", "BAD_REQUEST", 400);
    }
    public InvalidAccountException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
