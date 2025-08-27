package com.Ejada.TransactionService.application.exceptions;

public class NoTransactionList extends ApplicationException{
    public NoTransactionList(String id) {
        super("No transactions found for account ID " + id , "Not Found", 404);
    }

    public NoTransactionList(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
