package apis.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDetail {

    private String id;
    private String toAccountId;
    private double amount;
    private String description;
    private LocalDateTime timestamp;
}

