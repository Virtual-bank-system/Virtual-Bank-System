package apis.resources;

import application.enums.AccountType;
import application.enums.Status;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class AccountCreateRequest {

    @NotBlank(message = "User ID is required")
    @NotNull(message = "User ID is required")
    private String user_id;

    @NotBlank(message = "Account number is required")
    @NotNull(message = "Account number is required")

    // change size
    @Size(min=5, max = 20, message = "Account number must be between 5 and 20 characters")
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
