package apis.resources;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String error;
    private final String message;
}
