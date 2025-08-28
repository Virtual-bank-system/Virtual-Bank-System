package apis.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDetail {
    private String transactionId;
    private String toAccountId;
    private double amount;
    private String description;
    private LocalDateTime timestamp;
}

