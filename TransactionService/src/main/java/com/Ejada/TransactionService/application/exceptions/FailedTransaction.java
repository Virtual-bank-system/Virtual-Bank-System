package com.Ejada.TransactionService.application.exceptions;

public class FailedTransaction extends ApplicationException{
    public FailedTransaction() {
        super("Transaction has not been initiated", "Bad Request", 400);
    }

    public FailedTransaction(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
