package application.models;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class AccountCreateRequest {

    @NotBlank(message = "User ID is required")
    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    private String user_id;

    @NotBlank(message = "Account number is required")
    @NotNull(message = "Account number is required")
    @Size(min=5, max = 20, message = "Account number must be between 5 and 20 characters")
    @Positive(message = "Account number must be positive")
    private String account_number;

    @NotBlank(message = "Account type is required")
    @NotNull(message = "Account type is required")
    private AccountType account_type;

    @PositiveOrZero(message = "Balance cannot be negative")
    private double balance = 0.00;

    private Status status = Status.ACTIVE;

    private LocalDateTime created_at = LocalDateTime.now();

    private LocalDateTime updated_at = LocalDateTime.now();
}
