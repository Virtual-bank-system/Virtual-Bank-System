package application.exceptions;

public class InactiveAccountException extends ApplicationException {

    public InactiveAccountException() {
        super("Account must be active", "Bad Request", 400);
    }

    public InactiveAccountException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
