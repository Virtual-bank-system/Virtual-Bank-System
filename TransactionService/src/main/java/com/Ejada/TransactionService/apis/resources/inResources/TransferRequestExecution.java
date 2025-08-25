package com.Ejada.TransactionService.apis.resources.inResources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequestExecution {
    @NotBlank(message = "Transaction ID is required")
    @NotNull(message = "Transaction ID is required")
    private String transaction_id;
}
