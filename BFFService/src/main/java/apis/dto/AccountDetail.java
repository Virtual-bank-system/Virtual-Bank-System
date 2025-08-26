package apis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDetail {
    private String accountId;
    private String accountNumber;
    private String accountType;
    private double balance;
}

