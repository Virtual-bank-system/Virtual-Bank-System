package com.example.demo.apis.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountTransferRequest {

    @NotBlank(message = "From account id is required")
    @NotNull(message = "From account id is required")
    private String fromAccountId;

    @NotBlank(message = "To account id is required")
    @NotNull(message = "To account id is required")
    private String toAccountId;

    @Positive(message = "Amount must be positive")
    private Double amount;
}
