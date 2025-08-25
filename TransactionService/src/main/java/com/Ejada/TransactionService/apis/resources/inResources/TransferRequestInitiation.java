package com.Ejada.TransactionService.apis.resources.inResources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class TransferRequestInitiation {
    @NotBlank(message = "From Account ID is required")
    @NotNull(message = "From Account ID is required")
    private String from_account_id;

    @NotBlank(message = "To Account ID is required")
    @NotNull(message = "To Account ID is required")
    private String to_account_id;

    @Positive(message = "Amount must be positive")
    @NotBlank(message = "Amount is required")
    @NotNull(message = "Amount is required")
    private double amount;

    private String description;
}
