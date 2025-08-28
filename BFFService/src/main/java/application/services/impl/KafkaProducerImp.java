package application.services.impl;

import apis.dto.LogMessage;
import application.services.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImp implements KafkaProducer {

    private final KafkaTemplate<String, LogMessage> kafkaTemplate;

    public KafkaProducerImp(KafkaTemplate<String, LogMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(LogMessage logMessage) {
        kafkaTemplate.send("logging-topic", logMessage);
    }
}