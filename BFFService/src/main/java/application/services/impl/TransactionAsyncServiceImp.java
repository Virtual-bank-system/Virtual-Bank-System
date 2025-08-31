package application.services.impl;

import apis.resources.TransactionDetailList;
import application.feignClients.TransactionClient;
import application.services.TransactionAsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransactionAsyncServiceImp implements TransactionAsyncService {

    private final TransactionClient transactionClient;

    @Async("taskExecutor")
    public CompletableFuture<TransactionDetailList> getTransactionsAsync(String accountId) {

        TransactionDetailList transactionList = new TransactionDetailList();
        try {
            transactionList = transactionClient.getTransactionsList(accountId);
            return CompletableFuture.completedFuture(transactionList);
        }
        catch (Exception e) {
            transactionList.setTransactionDetailList(new ArrayList<>());
            return CompletableFuture.completedFuture(transactionList);
        }

    }
}
