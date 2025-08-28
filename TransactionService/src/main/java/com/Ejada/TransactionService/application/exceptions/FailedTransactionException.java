package com.Ejada.TransactionService.application.exceptions;

public class FailedTransactionException extends ApplicationException{
    public FailedTransactionException() {
        super("Transaction must be initiated", "Bad Request", 400);
    }

    public FailedTransactionException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
