package application.services;

import apis.resources.outResources.TransactionDetailList;
import apis.resources.outResources.TransferResponse;
import application.exceptions.FailedTransactionException;
import application.exceptions.InsufficientFundsException;


public interface TransactionService {
    TransferResponse initiateTransaction(String fromAccountId, String toAccountId, double amount, String description) throws InsufficientFundsException;

    TransferResponse executeTransaction(String transactionId) throws FailedTransactionException;

    TransactionDetailList getTransactionsList(String accountId);
}
