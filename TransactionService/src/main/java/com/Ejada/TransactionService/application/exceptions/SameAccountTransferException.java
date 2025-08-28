package com.Ejada.TransactionService.application.exceptions;

public class SameAccountTransferException extends ApplicationException{

    public SameAccountTransferException() {
        super("From Account Id and To Account Id must differ", "Bad Request", 400);
    }

    public SameAccountTransferException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
