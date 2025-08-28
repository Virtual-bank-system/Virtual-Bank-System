package application.services;

import apis.dto.LogMessage;

public interface KafkaProducer {
    void sendLog(LogMessage logMessage);
}
