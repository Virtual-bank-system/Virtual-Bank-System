package com.example.demo.apis.resources;

import com.example.demo.application.validators.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountCreation {

    @NotBlank(message = "User ID is required")
    @NotNull(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Account type is required")
    @NotNull(message = "Account type is required")
    @AccountType
    private String accountType;

    @NotNull(message = "Initial balance is required")
    private Double initialBalance;
}
