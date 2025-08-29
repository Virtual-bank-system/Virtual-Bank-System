package application.exceptions;

public class UserAlreadyExistsException extends ApplicationException {
    public UserAlreadyExistsException() {
        super("Username or email already exists.", "CONFLICT", 409);
    }

    public UserAlreadyExistsException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
