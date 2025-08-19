package apis.resources;

import application.enums.MessageType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class LoggingCreateRequest {

    @NotBlank(message = "Message is required")
    @NotNull(message = "Message is required")
    private String message;

    @NotBlank(message = "Message Type is required")
    @NotNull(message = "Message Type is required")
    private MessageType message_type;

    @NotBlank(message = "Date Time is required")
    @NotNull(message = "Date Time is required")
    private LocalDateTime date_time;
}
