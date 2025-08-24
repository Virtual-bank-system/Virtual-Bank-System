package com.Ejada.TransactionService.application.services.impl;

import com.Ejada.TransactionService.apis.resources.outResources.TransactionExecutionResponse;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionHistoryListResponse;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionHistoryResponse;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionInitiationResponse;
import com.Ejada.TransactionService.application.enums.Status;
import com.Ejada.TransactionService.application.exceptions.GlobalExceptionHandler;
import com.Ejada.TransactionService.application.exceptions.InsufficientFunds;
import com.Ejada.TransactionService.application.exceptions.TransactionNotFoundException;
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

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private Mapper mapper;

    @Autowired
    private AccountClient accountClient;

    public TransactionInitiationResponse initiateTransaction(String fromAccountId, String toAccountId, double amount, String description){
        // 1- check if from account id exists
        // 2- check if to account id exists
        // 3- check amount is valid : non-negative , greater than balance in from account id
        //4- if valid, save it
        ResponseEntity<AccountDetail> from = accountClient.getAccountById(fromAccountId);
        ResponseEntity<AccountDetail> to = accountClient.getAccountById(toAccountId);

//        if (amount <= 0) {
//            throw new GlobalExceptionHandler("Amount must be greater than 0");
//        }
//
        double currentAmount = from.getBody().getBalance();

        if(amount < currentAmount){
             throw new InsufficientFunds();
        }
        Transaction transaction = new Transaction();

        transaction.setFrom_account_id(fromAccountId);
        transaction.setTo_account_id(toAccountId);

        // amount or negative amount ??
        transaction.setAmount(amount);
        transaction.setDescription(description);
        // status default already initiated

        transactionRepo.save(transaction);

        TransactionInitiationResponse response = mapper.toTransactionInitiationResponse(transaction);

        return response;
    }

    public TransactionExecutionResponse executeTransaction(String transactionId) {
      // Check id
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException());

//        if (!transaction.getStatus().equals(Status.INITIATED)) {
//            throw new InvalidTransactionStateException("Transaction not in INITIATED state");
//        }

        try {
            // Debit from account
            AccountTransferRequest debitRequest = new AccountTransferRequest(
                    transaction.getFrom_account_id(),
                    transaction.getTo_account_id(),
                    -transaction.getAmount() // negative for debit
            );

            accountClient.transfer(debitRequest);

            // Credit to account
            AccountTransferRequest creditRequest = new AccountTransferRequest(
                    transaction.getTo_account_id(),
                    transaction.getFrom_account_id(),
                    transaction.getAmount() // positive for credit
            );

            accountClient.transfer(creditRequest);

            // 4. Update transaction as SUCCESS
            transaction.setStatus(Status.SUCCESS);
            transaction.setTimestamp(LocalDateTime.now());
            transactionRepo.save(transaction);

            return new TransactionExecutionResponse(
                    transaction.getId(),
                    Status.SUCCESS,
                    transaction.getTimestamp()
            );
        } catch (Exception ex) {
            // On failure
            transaction.setStatus(Status.FAILED);
            transaction.setTimestamp(LocalDateTime.now());
            transactionRepo.save(transaction);

          //  throw new BadRequestException("Invalid 'from' or 'to' account ID, or insufficient funds.");
        }
        return null;
    }


    public TransactionHistoryListResponse getTransactionsList(String accountId){
        // check on account id
        ResponseEntity<AccountDetail> accountDetail = accountClient.getAccountById(accountId);

        List<Transaction> fromTransactions = transactionRepo.findByFrom_account_id(accountId);
        List<Transaction> toTransactions = transactionRepo.findByTo_account_id(accountId);

        List<TransactionHistoryResponse> fromResponses = mapper.toTransactionResponseList(fromTransactions);
        List<TransactionHistoryResponse> toResponses = mapper.toTransactionResponseList(toTransactions);

        // Combine both lists
        List<TransactionHistoryResponse> allResponses = new ArrayList<>();
        allResponses.addAll(fromResponses);
        allResponses.addAll(toResponses);

        // Build response
        TransactionHistoryListResponse response = new TransactionHistoryListResponse();
        response.setTransaction_response_list(allResponses);

        return response;
    }
}
