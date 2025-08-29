package application.exceptions;

public class InvalidUserException extends ApplicationException {
    public InvalidUserException() {
        super("Invalid username or password.", "UNAUTHORIZED", 401);
    }

    public InvalidUserException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
