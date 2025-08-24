package com.Ejada.TransactionService.application.exceptions;

public class TransactionNotFoundException extends ApplicationException{
    public TransactionNotFoundException() {
        super("Insufficient funds for this transaction", "INSUFFICIENT_FUNDS", 400);
    }

    public TransactionNotFoundException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
