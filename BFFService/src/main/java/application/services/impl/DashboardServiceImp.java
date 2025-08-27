package application.services.impl;

import apis.dto.*;
import application.exceptions.FailedRetrieveException;
import application.feignClients.AccountClient;
import application.feignClients.TransactionClient;
import application.feignClients.UserClient;
import application.mapper.Mapper;
import application.services.DashboardService;
import application.services.TransactionAsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImp implements DashboardService {

    private final UserClient userClient;
    private final AccountClient accountClient;
    private final TransactionAsyncService transactionAsyncService;
    private final Mapper mapper;

    public DashboardResponse getDashboard(String userId) {

        UserProfile userProfile = userClient.getProfile(userId);
        try {
            List<AccountDetail> accountDetailList = accountClient.getAccountsByUser(userId);

            // Async calls
            List<CompletableFuture<AccountWithTransactions>> futures = accountDetailList.stream()
                    .map(account -> transactionAsyncService.getTransactionsAsync(account.getAccountId())
                            .thenApply(txnList ->
                                    mapper.toAccountWithTransactions(account, txnList.getTransactionDetailList())
                            ))
                    .toList();

            // Wait for all
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

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

