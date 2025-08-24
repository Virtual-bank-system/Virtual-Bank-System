package application.exceptions;

public class InsufficientFundsException extends ApplicationException {
    public InsufficientFundsException() {
        super("Insufficient funds", "INSUFFICIENT_FUNDS", 402);
    }
    public InsufficientFundsException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
