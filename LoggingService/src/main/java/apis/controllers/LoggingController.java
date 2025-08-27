package apis.controllers;

import apis.resources.LogMessage;
import application.enums.MessageType;
import application.services.KafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/logs")
public class LoggingController {
    private final KafkaProducer kafkaProducer;

    public LoggingController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/send")
    public String sendLog(@RequestBody String message) {
        LogMessage logMessage = new LogMessage(message, MessageType.Request, LocalDateTime.now());
        kafkaProducer.sendLog(logMessage);
        return "Log sent!";
    }
}
