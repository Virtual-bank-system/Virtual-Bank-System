package application.exceptions;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException() {
        super("User not found.", "NOT_FOUND", 404);
    }

    public UserNotFoundException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
