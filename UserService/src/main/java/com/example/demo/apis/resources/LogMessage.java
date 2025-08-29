package com.example.demo.apis.resources;

import com.example.demo.application.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LogMessage {
    private String message;
    private MessageType messageType;
    private String dateTime;
}