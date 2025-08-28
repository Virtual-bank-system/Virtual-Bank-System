package com.Ejada.TransactionService.application.services.impl;

import com.Ejada.TransactionService.apis.resources.outResources.TransactionDetail;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionDetailList;
import com.Ejada.TransactionService.apis.resources.outResources.TransferResponse;
import com.Ejada.TransactionService.application.enums.DeliveryStatus;
import com.Ejada.TransactionService.application.enums.Status;
import com.Ejada.TransactionService.application.exceptions.InsufficientFundsException;
import com.Ejada.TransactionService.application.exceptions.FailedTransactionException;
import com.Ejada.TransactionService.application.exceptions.NoTransactionListException;
import com.Ejada.TransactionService.application.exceptions.SameAccountTransferException;
import com.Ejada.TransactionService.application.feign.AccountClient;
import com.Ejada.TransactionService.application.feign.dto.AccountDetail;
import com.Ejada.TransactionService.application.feign.dto.AccountTransferRequest;
import com.Ejada.TransactionService.application.mappers.Mapper;
import com.Ejada.TransactionService.application.models.Transaction;
import com.Ejada.TransactionService.application.repos.TransactionRepo;
import com.Ejada.TransactionService.application.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;

    private final Mapper mapper;

    private final AccountClient accountClient;

    public TransferResponse initiateTransaction(String fromAccountId, String toAccountId, double amount, String description){
        // 1- check if from account id exists
        // 2- check if to account id exists
        // 3- check amount is valid : non-negative , greater than balance in from account id
        // 4- if valid, save it
        AccountDetail from = accountClient.getAccountById(fromAccountId);
        AccountDetail to = accountClient.getAccountById(toAccountId);

        if (fromAccountId.equals(toAccountId)){
            throw new SameAccountTransferException();
        }
        double currentBalance = from.getBalance();

        if(amount > currentBalance){
             throw new InsufficientFundsException();
        }

        Transaction transaction = new Transaction();

        transaction.setFromAccountId(fromAccountId);
        transaction.setToAccountId(toAccountId);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setStatus(Status.INITIATED);
        transaction.setTimestamp(LocalDateTime.now());

        Transaction saved = transactionRepo.save(transaction);
//        System.out.println("Generated ID: " + saved.getTransactionId());
        TransferResponse response = mapper.toTransferResponse(saved);

        return response;
    }

    public TransferResponse executeTransaction(String transactionId) {
        // Check ID
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new FailedTransactionException());

        if (transaction.getStatus() != Status.INITIATED) {
            throw new FailedTransactionException();
        }

        try {
            // Debit
            AccountTransferRequest request = new AccountTransferRequest(
                    transaction.getFromAccountId(),
                    transaction.getToAccountId(),
                    transaction.getAmount()
            );
            accountClient.transfer(request);


            // Update transaction to SUCCESS
            transaction.setStatus(Status.SUCCESS);
            transaction.setTimestamp(LocalDateTime.now());
            transactionRepo.save(transaction);

            return new TransferResponse(
                    transaction.getTransactionId(),
                    Status.SUCCESS,
                    transaction.getTimestamp()
            );

        } catch (Exception ex) {
            // Mark transaction as FAILED
            transaction.setStatus(Status.FAILED);
            transaction.setTimestamp(LocalDateTime.now());
            transactionRepo.save(transaction);

            // Rethrow the exception
            throw ex;
        }
    }

    public TransactionDetailList getTransactionsList(String accountId) {
        // check on account id
        AccountDetail accountDetail = accountClient.getAccountById(accountId);

        List<Transaction> fromTransactions = transactionRepo.findByFromAccountId(accountId);
        List<Transaction> toTransactions = transactionRepo.findByToAccountId(accountId);

        if (fromTransactions.isEmpty() && toTransactions.isEmpty()) {
            throw new NoTransactionListException(accountId);
        }

        // Convert "from" transactions → SUCCESS or FAILED
        List<TransactionDetail> fromResponses = fromTransactions.stream()
                .map(transaction -> {
                    TransactionDetail res = mapper.toTransactionDetail(transaction);
                    res.setAmount(-transaction.getAmount());

                    if (transaction.getStatus() == Status.SUCCESS) {
                        res.setDeliveryStatus(DeliveryStatus.SENT);
                    }
                    else {
                        res.setDeliveryStatus(DeliveryStatus.FAILED);
                    }
                    res.setDescription(transaction.getDescription() == null ? "" : transaction.getDescription());
                    return res;
                })
                .collect(Collectors.toList());

        // Convert "to" transactions → SUCCESS or FAILED
        List<TransactionDetail> toResponses = toTransactions.stream()
                .map(tx -> {
                    TransactionDetail res = mapper.toTransactionDetail(tx);

                    if (tx.getStatus() == Status.SUCCESS) {
                        res.setDeliveryStatus(DeliveryStatus.DELIVERED);
                    }
                    else {
                        res.setDeliveryStatus(DeliveryStatus.FAILED);
                    }
                    return res;
                })
                .collect(Collectors.toList());

        // Combine both lists
        List<TransactionDetail> allResponses = new ArrayList<>();
        allResponses.addAll(fromResponses);
        allResponses.addAll(toResponses);

        // Sort by timestamp descending
        allResponses.sort(Comparator.comparing(TransactionDetail::getTimestamp).reversed());

        // Build response
        TransactionDetailList response = new TransactionDetailList();
        response.setTransactionDetailList(allResponses);

        return response;
    }

}
