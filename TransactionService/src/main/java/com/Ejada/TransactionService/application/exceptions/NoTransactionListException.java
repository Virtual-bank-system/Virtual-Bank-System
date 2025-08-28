package com.Ejada.TransactionService.application.exceptions;

public class NoTransactionListException extends ApplicationException{
    public NoTransactionListException(String id) {
        super("No transactions found for account ID " + id , "Not Found", 404);
    }

    public NoTransactionListException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
