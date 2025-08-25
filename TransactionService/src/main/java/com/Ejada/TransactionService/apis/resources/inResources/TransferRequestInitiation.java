package com.Ejada.TransactionService.apis.resources.inResources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferRequestInitiation {
    @NotBlank(message = "From Account ID is required")
    @NotNull(message = "From Account ID is required")
    private String fromAccountId;

    @NotBlank(message = "To Account ID is required")
    @NotNull(message = "To Account ID is required")
    private String toAccountId;

    @Positive(message = "Amount must be positive")
    @NotNull(message = "Amount is required")
    private double amount;

    private String description;
}
