package apis.dto;

import application.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LogMessage {
    private String message;
    private MessageType message_type;
    private LocalDateTime date_time;
}