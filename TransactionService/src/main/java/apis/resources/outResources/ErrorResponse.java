package apis.resources.outResources;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private final int httpStatus;
    private final String errorCode;
    private final String errorMessage;
}
