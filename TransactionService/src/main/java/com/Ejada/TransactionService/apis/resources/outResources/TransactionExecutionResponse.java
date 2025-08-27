package com.Ejada.TransactionService.apis.resources.outResources;

import com.Ejada.TransactionService.application.enums.Status;

import java.time.LocalDateTime;

public class TransactionExecutionResponse {
    private String id;
    private Status status;
    private LocalDateTime timestamp;
}
