package com.Ejada.TransactionService.apis.resources.outResources;

import com.Ejada.TransactionService.application.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class TransactionInitiationResponse {
    private String id;

    // "Initiated"
    private Status status;

    private LocalDateTime timestamp;
}
