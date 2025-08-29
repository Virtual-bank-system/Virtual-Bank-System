package application.services;

import apis.resources.TransactionDetailList;

import java.util.concurrent.CompletableFuture;

public interface TransactionAsyncService {
    CompletableFuture<TransactionDetailList> getTransactionsAsync(String accountId);
}
