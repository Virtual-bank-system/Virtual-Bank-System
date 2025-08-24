package com.Ejada.TransactionService.application.services;

import com.Ejada.TransactionService.apis.resources.outResources.TransactionExecutionResponse;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionHistoryListResponse;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionInitiationResponse;


public interface TransactionService {
    TransactionInitiationResponse initiateTransaction(String fromAccountId, String toAccountId, double amount, String description);

    TransactionExecutionResponse executeTransaction(String transactionId);

    TransactionHistoryListResponse getTransactionsList(String accountId);
}
