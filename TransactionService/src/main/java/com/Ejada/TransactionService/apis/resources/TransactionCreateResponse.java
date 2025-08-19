package com.Ejada.TransactionService.apis.resources;

import com.Ejada.TransactionService.application.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public class TransactionCreateResponse {

    private String id;
    private String from_account_id;
    private String to_account_id;
    private double amount;
    private Status status;
    private String description;
    private LocalDateTime timestamp;

}
