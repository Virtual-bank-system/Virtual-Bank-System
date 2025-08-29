package apis.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetail {
    private String accountId;
    private String accountNumber;
    private String accountType;
    private double balance;
}

