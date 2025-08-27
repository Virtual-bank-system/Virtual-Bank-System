package apis.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountWithTransactions {
    private String accountId;
    private String accountNumber;
    private String accountType;
    private double balance;
    private TransactionDetailList transactions;
}
