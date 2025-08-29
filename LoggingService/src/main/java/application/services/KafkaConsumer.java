package application.services;

import apis.resources.LogMessage;
import application.mappers.Mapper;
import application.models.Logging;
import application.repos.LoggingRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
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

        Logging logging = new Logging();
        logging.setMessage(logMessage.getMessage());
        logging.setMessageType(logMessage.getMessageType());
        logging.setDateTime(logMessage.getDateTime());
        loggingRepo.save(logging);
    }

//    @KafkaListener(topics = "logging-topic", groupId = "logging-group")
//    public void debugListener(String message) {
//        System.out.println("RAW message: " + message);
//    }
}

