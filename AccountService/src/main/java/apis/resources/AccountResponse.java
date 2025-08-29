package apis.resources;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponse {
    private String accountId;
    private String accountNumber;
    private String message;
}
