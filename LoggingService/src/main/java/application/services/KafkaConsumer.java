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
    private final Mapper mapper;

    public KafkaConsumer(LoggingRepo loggingRepo, Mapper mapper) {
        this.loggingRepo = loggingRepo;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "logging-topic", groupId = "logging-group")
    public void consumeLog(LogMessage logMessage) {
        Logging logging = mapper.toLogging(logMessage);
        loggingRepo.save(logging);
    }
}
