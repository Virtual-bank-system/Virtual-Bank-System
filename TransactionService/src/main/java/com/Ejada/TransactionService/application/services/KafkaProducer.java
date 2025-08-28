package com.Ejada.TransactionService.application.services;


import com.Ejada.TransactionService.apis.resources.outResources.LogMessage;

public interface KafkaProducer {
    void sendLog(LogMessage logMessage);
}
