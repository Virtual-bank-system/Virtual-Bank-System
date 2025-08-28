package com.Ejada.TransactionService.application.exceptions;

public class InsufficientFundsException extends ApplicationException{

    public InsufficientFundsException() {
        super("Insufficient funds for this transaction", "Bad Request", 400);
    }

    public InsufficientFundsException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
