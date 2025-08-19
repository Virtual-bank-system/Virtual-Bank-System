package apis.resources;

import application.enums.MessageType;

import java.time.LocalDateTime;

public class LoggingCreateResponse {
    private String message;
    private MessageType message_type;
    private LocalDateTime date_time;
}
