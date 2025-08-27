package application.services.impl;

import apis.dto.TransactionDetailList;
import application.feignClients.TransactionClient;
import application.services.TransactionAsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransactionAsyncServiceImp implements TransactionAsyncService {

    private final TransactionClient transactionClient;

    @Async
    public CompletableFuture<TransactionDetailList> getTransactionsAsync(String accountId) {
        TransactionDetailList transactionList = transactionClient.getTransactionsList(accountId);
        return CompletableFuture.completedFuture(transactionList);
    }
}
