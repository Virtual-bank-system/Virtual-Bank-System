package application.services;

import apis.dto.TransactionDetailList;

import java.util.concurrent.CompletableFuture;

public interface TransactionAsyncService {
    CompletableFuture<TransactionDetailList> getTransactionsAsync(String accountId);
}
