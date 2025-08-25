package com.Ejada.TransactionService.application.services;

import com.Ejada.TransactionService.apis.resources.outResources.TransactionDetailList;
import com.Ejada.TransactionService.apis.resources.outResources.TransferResponse;
import com.Ejada.TransactionService.application.exceptions.FailedTransaction;
import com.Ejada.TransactionService.application.exceptions.InsufficientFunds;


public interface TransactionService {
    TransferResponse initiateTransaction(String fromAccountId, String toAccountId, double amount, String description) throws InsufficientFunds;

    TransferResponse executeTransaction(String transactionId) throws FailedTransaction;

    TransactionDetailList getTransactionsList(String accountId);
}
