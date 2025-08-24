package application.exceptions;

public class AccountNotFoundException extends ApplicationException {
    public AccountNotFoundException() {
        super("Account not found", "ACCOUNT_NOT_FOUND", 404);
    }
    public AccountNotFoundException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}

