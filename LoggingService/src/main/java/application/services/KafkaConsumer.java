package application.services;

import apis.resources.LogMessage;
import application.models.Logs;
import application.repos.LoggingRepo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final LoggingRepo loggingRepo;

    public KafkaConsumer(LoggingRepo loggingRepo) {
        this.loggingRepo = loggingRepo;
    }

    @KafkaListener(topics = "logging-topic", groupId = "logging-group", containerFactory = "kafkaListenerContainerFactory")

    public void consumeLog(LogMessage logMessage) {
        System.out.println("Consumed message: " + logMessage);

        Logs logs = new Logs();
        logs.setMessage(logMessage.getMessage());
        logs.setMessageType(logMessage.getMessageType());
        logs.setDateTime(logMessage.getDateTime());

        loggingRepo.save(logs);
        System.out.println("Logging: " + logs);
    }

}

