package com.example.demo.application.services;

import com.example.demo.apis.resources.LogMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, LogMessage> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, LogMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(LogMessage logMessage) {
        kafkaTemplate.send("logging-topic", logMessage);
    }
}