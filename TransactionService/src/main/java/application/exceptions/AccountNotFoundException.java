package application.exceptions;

public class AccountNotFoundException extends ApplicationException{

    public AccountNotFoundException() {
        super("Invalid 'from' or 'to' account ID.", "Bad Request", 400);
    }

    public AccountNotFoundException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }

}
