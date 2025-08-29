package application.exceptions;

public class AccountNotFoundException extends ApplicationException {
    public AccountNotFoundException() {
        super("Invalid 'from' or 'to' account ID.", "BAD_REQUEST", 400);
    }
    public AccountNotFoundException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}

