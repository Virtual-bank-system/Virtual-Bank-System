package application.services;

import apis.resources.LogMessage;

public interface KafkaProducer {
    void sendLog(LogMessage logMessage);
}
