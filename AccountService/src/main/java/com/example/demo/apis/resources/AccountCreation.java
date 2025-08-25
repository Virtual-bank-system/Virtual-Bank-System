package com.example.demo.apis.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class AccountCreation {

    @NotBlank(message = "User ID is required")
    @NotNull(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Account type is required")
    @NotNull(message = "Account type is required")
    private String accountType;

    @NotNull
    @PositiveOrZero(message = "Balance cannot be negative")
    private Double initialBalance;
}
