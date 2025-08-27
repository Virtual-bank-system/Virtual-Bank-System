package apis.resources;

import application.enums.MessageType;

import java.time.LocalDateTime;

public class LoggingCreateResponse {
    private String message;
    private MessageType messageType;
    private LocalDateTime localDateTime;
}
