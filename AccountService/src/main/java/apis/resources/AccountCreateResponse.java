package apis.resources;

import application.enums.AccountType;
import application.enums.Status;

import java.time.LocalDateTime;

public class AccountCreateResponse {

    private String user_id;

    private String account_number;

    private AccountType account_type;

    private double balance = 0.00;

    private Status status = Status.ACTIVE;

    private LocalDateTime created_at = LocalDateTime.now();

    private LocalDateTime updated_at = LocalDateTime.now();
}
