package apis.resources;

import application.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogMessage {
    private String message;
    private MessageType messageType;
    private String dateTime;
}