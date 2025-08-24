package com.Ejada.TransactionService.apis.resources.outResources;

import com.Ejada.TransactionService.application.enums.Status;

import java.time.LocalDateTime;

public class TransactionHistoryResponse {

    private String id;
    private String from_account_id;
    private String to_account_id;
    private double amount;
    private Status status;
    private String description;
    private LocalDateTime timestamp;

}
