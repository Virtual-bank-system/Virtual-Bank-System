package application.services.impl;

import apis.dto.AccountDetail;
import apis.dto.DashboardResponse;
import apis.dto.TransactionDetailList;
import apis.dto.UserProfile;
import application.exceptions.FailedRetrieveException;
import application.feignClients.AccountClient;
import application.feignClients.TransactionClient;
import application.feignClients.UserClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class DashboardServiceImp {

    private final UserClient userClient;
    private final AccountClient accountClient;
    private final TransactionClient transactionClient;

    // will change it
    private final Executor executor = Executors.newFixedThreadPool(5);

    public DashboardServiceImp(UserClient userClient, AccountClient accountClient, TransactionClient transactionClient) {
        this.userClient = userClient;
        this.accountClient = accountClient;
        this.transactionClient = transactionClient;
    }

    public DashboardResponse getDashboard(String userId) {

        UserProfile userProfile = userClient.getProfile(userId);

        try {
            List<AccountDetail> accountDetailList = accountClient.getAccountsByUser(userId);

            // asynch ??
            TransactionDetailList transactionDetailList = transactionClient.getTransactionsList(userId);

            DashboardResponse response = new DashboardResponse();

            response.setUserProfile(userProfile);
            response.setAccountDetailList(accountDetailList);
            response.setTransactionDetailList(transactionDetailList);

            return response;
        }
        catch (Exception e) {
            throw new FailedRetrieveException();
        }
    }

}
