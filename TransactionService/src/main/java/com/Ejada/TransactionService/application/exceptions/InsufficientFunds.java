package com.Ejada.TransactionService.application.exceptions;

public class InsufficientFunds extends ApplicationException{

    public InsufficientFunds() {
        super("Insufficient funds for this transaction", "INSUFFICIENT_FUNDS", 400);
    }

    public InsufficientFunds(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
