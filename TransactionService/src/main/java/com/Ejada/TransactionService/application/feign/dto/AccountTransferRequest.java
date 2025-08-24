package com.Ejada.TransactionService.application.feign.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountTransferRequest {

    @NotBlank(message = "From Account ID is required")
    @NotNull(message = "From Account ID is required")
    private String from_account_id;

    @NotBlank(message = "To Account ID is required")
    @NotNull(message = "To Account ID is required")
    private String to_account_id;

    @PositiveOrZero(message = "Amount cannot be negative")
    @NotBlank(message = "Amount is required")
    @NotNull(message = "Amount is required")
    private double amount;
}
