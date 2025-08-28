package com.Ejada.TransactionService.apis.resources.outResources;

import com.Ejada.TransactionService.application.enums.DeliveryStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDetail {

    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private double amount;
    private String description;
    private LocalDateTime timestamp;
    private DeliveryStatus deliveryStatus;
}
