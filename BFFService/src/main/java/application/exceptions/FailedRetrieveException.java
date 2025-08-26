package application.exceptions;

public class FailedRetrieveException  extends ApplicationException{

    public FailedRetrieveException()
    {
        super("Failed to retrieve dashboard data due to an issue with downstream services.",
               "Internal Server Error",
                500 );
    }
    public FailedRetrieveException(String message, String errorCode, int httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
