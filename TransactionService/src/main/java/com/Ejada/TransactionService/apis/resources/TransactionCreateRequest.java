package com.Ejada.TransactionService.apis.resources;

import com.Ejada.TransactionService.application.enums.Status;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class TransactionCreateRequest {

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

    private String description;

}
