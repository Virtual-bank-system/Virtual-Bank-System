package com.example.demo.application.exceptions;

public class InsufficientFundsException extends ApplicationException {
    public InsufficientFundsException() {
        super("Insufficient funds.", "BAD_REQUEST", 400);
    }
    public InsufficientFundsException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
