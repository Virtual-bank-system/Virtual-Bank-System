package apis.resources;

import lombok.Data;

@Data
public class AccountWithTransactions {
    private String accountId;
    private String accountNumber;
    private String accountType;
    private double balance;
    private TransactionDetailList transactions;
}
