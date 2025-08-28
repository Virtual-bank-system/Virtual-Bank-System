package application.services.impl;

import apis.dto.*;
import application.exceptions.FailedRetrieveException;
import application.feignClients.AccountClient;
import application.feignClients.UserClient;
import application.mapper.AccountMapper;
import application.services.DashboardService;
import application.services.TransactionAsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DashboardServiceImp implements DashboardService {

    private final UserClient userClient;
    private final AccountClient accountClient;
    private final TransactionAsyncService transactionAsyncService;
    private final AccountMapper accountMapper;


    public DashboardResponse getDashboard(String userId) {
        try {
            // Fetch user profile
            UserProfile userProfile = userClient.getProfile(userId);


            // Fetch accounts for user
            List<AccountDetail> accountDetailList = accountClient.getAccountsByUser(userId);

            // Async calls for transactions
            List<CompletableFuture<AccountWithTransactions>> futures = accountDetailList.stream()
                    .map(account -> transactionAsyncService.getTransactionsAsync(account.getAccountId())
                            .thenApply(txnList ->
                                    accountMapper.toAccountWithTransactions(account, txnList.getTransactionDetailList())
                            ))
                    .toList();

            // Wait for all to complete
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            // Collect results
            List<AccountWithTransactions> accountsWithTxns = futures.stream()
                    .map(CompletableFuture::join)
                    .toList();

            DashboardResponse response = new DashboardResponse();
            response.setUserProfile(userProfile);
            response.setAccounts(accountsWithTxns);
            return response;

        }
        catch (Exception e) {
            throw new FailedRetrieveException();
        }
    }
}

