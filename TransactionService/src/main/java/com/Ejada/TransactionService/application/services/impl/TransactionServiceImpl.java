package com.Ejada.TransactionService.application.services.impl;

import com.Ejada.TransactionService.apis.resources.outResources.TransactionDetail;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionDetailList;
import com.Ejada.TransactionService.apis.resources.outResources.TransferResponse;
import com.Ejada.TransactionService.application.enums.DeliveryStatus;
import com.Ejada.TransactionService.application.enums.Status;
import com.Ejada.TransactionService.application.exceptions.InsufficientFunds;
import com.Ejada.TransactionService.application.exceptions.FailedTransaction;
import com.Ejada.TransactionService.application.exceptions.NoTransactionList;
import com.Ejada.TransactionService.application.feign.AccountClient;
import com.Ejada.TransactionService.application.feign.dto.AccountDetail;
import com.Ejada.TransactionService.application.feign.dto.AccountTransferRequest;
import com.Ejada.TransactionService.application.mappers.Mapper;
import com.Ejada.TransactionService.application.models.Transaction;
import com.Ejada.TransactionService.application.repos.TransactionRepo;
import com.Ejada.TransactionService.application.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private Mapper mapper;

    @Autowired
    private AccountClient accountClient;

    public TransferResponse initiateTransaction(String fromAccountId, String toAccountId, double amount, String description){
        // 1- check if from account id exists
        // 2- check if to account id exists
        // 3- check amount is valid : non-negative , greater than balance in from account id
        // 4- if valid, save it
        AccountDetail from = accountClient.getAccountById(fromAccountId);
        AccountDetail to = accountClient.getAccountById(toAccountId);

        double currentBalance = from.getBalance();

        if(amount > currentBalance){
             throw new InsufficientFunds();
        }

        Transaction transaction = new Transaction();

        transaction.setFromAccountId(fromAccountId);
        transaction.setToAccountId(toAccountId);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setStatus(Status.INITIATED);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepo.save(transaction);

        TransferResponse response = mapper.toTransferResponse(transaction);

        return response;
    }

    public TransferResponse executeTransaction(String transactionId) {
        // Check ID
        Transaction transaction = transactionRepo.findById(transactionId)
                ///  ????
                .orElseThrow(() -> new FailedTransaction());

        try {
            //  Debit from account
            AccountTransferRequest debitRequest = new AccountTransferRequest(
                    transaction.getFromAccountId(),
                    transaction.getToAccountId(),
                    -transaction.getAmount() // negative as debit
            );
            accountClient.transfer(debitRequest);

            //  Credit to account
            AccountTransferRequest creditRequest = new AccountTransferRequest(
                    transaction.getToAccountId(),
                    transaction.getFromAccountId(),
                    transaction.getAmount() // positive for credit
            );

            accountClient.transfer(creditRequest);

            // Update transaction to SUCCESS
            transaction.setStatus(Status.SUCCESS);
            transaction.setTimestamp(LocalDateTime.now());
            transactionRepo.save(transaction);

            return new TransferResponse(
                    transaction.getId(),
                    Status.SUCCESS,
                    transaction.getTimestamp()
            );
        }
        catch (Exception e) {
            transaction.setStatus(Status.FAILED);
            transaction.setTimestamp(LocalDateTime.now());
            transactionRepo.save(transaction);
            throw new FailedTransaction(e.getMessage(),"Bad Request",400);
        }
    }

    public TransactionDetailList getTransactionsList(String accountId){
        // check on account id
        AccountDetail accountDetail = accountClient.getAccountById(accountId);

        List<Transaction> fromTransactions = transactionRepo.findByFromAccountId(accountId);
        List<Transaction> toTransactions = transactionRepo.findByToAccountId(accountId);

        if (fromTransactions.isEmpty() && toTransactions.isEmpty()) {
            throw new NoTransactionList(accountId);
        }

        // 3 - Convert "from" transactions to response → mark as negative + SENT
        List<TransactionDetail> fromResponses = fromTransactions.stream()
                .map(transaction -> {
                    TransactionDetail res = mapper.toTransactionResponse(transaction);
                    res.setAmount(-transaction.getAmount());
                    res.setDeliveryStatus(DeliveryStatus.SENT);
                    return res;
                })
                .collect(Collectors.toList());

        // 4 - Convert "to" transactions to response → keep positive + DELIVERED
        List<TransactionDetail> toResponses = toTransactions.stream()
                .map(tx -> {
                    TransactionDetail res = mapper.toTransactionResponse(tx);
                    res.setDeliveryStatus(DeliveryStatus.DELIVERED);
                    return res;
                })
                .collect(Collectors.toList());

        // Combine both lists
        List<TransactionDetail> allResponses = new ArrayList<>();
        allResponses.addAll(fromResponses);
        allResponses.addAll(toResponses);

        // sort by timestamp descending
        allResponses.sort(Comparator.comparing(TransactionDetail::getTimestamp).reversed());


        // Build response
        TransactionDetailList response = new TransactionDetailList();
        response.setTransaction_response_list(allResponses);

        return response;
    }
}
