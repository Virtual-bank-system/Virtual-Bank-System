package com.Ejada.TransactionService.application.services;

import com.Ejada.TransactionService.apis.resources.outResources.TransactionDetailList;
import com.Ejada.TransactionService.apis.resources.outResources.TransferResponse;
import com.Ejada.TransactionService.application.exceptions.FailedTransactionException;
import com.Ejada.TransactionService.application.exceptions.InsufficientFundsException;


public interface TransactionService {
    TransferResponse initiateTransaction(String fromAccountId, String toAccountId, double amount, String description) throws InsufficientFundsException;

    TransferResponse executeTransaction(String transactionId) throws FailedTransactionException;

    TransactionDetailList getTransactionsList(String accountId);
}
