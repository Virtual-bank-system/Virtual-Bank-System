package apis.resources;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private final int httpStatus;
    private final String errorCode;
    private final String errorMessage;
}
