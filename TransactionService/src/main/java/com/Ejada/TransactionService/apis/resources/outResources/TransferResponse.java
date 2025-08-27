package com.Ejada.TransactionService.apis.resources.outResources;

import com.Ejada.TransactionService.application.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private String transactionId;
    private Status status;
    private LocalDateTime timestamp;
}
