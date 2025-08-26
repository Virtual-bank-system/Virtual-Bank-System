package apis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
    private  UserProfile userProfile;
    private List<AccountDetail> accountDetailList;
    private TransactionDetailList transactionDetailList;
}
