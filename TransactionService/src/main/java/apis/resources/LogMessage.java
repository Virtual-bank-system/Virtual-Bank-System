package apis.resources;

import application.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogMessage {
    private String message;
    private MessageType messageType;
    private String dateTime;
}