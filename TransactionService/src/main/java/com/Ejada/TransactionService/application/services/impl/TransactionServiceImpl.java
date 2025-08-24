package com.Ejada.TransactionService.application.services.impl;

import com.Ejada.TransactionService.apis.resources.outResources.TransactionExecutionResponse;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionHistoryListResponse;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionInitiationResponse;
import com.Ejada.TransactionService.application.feign.AccountClient;
import com.Ejada.TransactionService.application.feign.UserClient;
import com.Ejada.TransactionService.application.mappers.Mapper;
import com.Ejada.TransactionService.application.models.Transaction;
import com.Ejada.TransactionService.application.repos.TransactionRepo;
import com.Ejada.TransactionService.application.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private Mapper mapper;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private UserClient userClient;


    public TransactionInitiationResponse initiateTransaction(String fromAccountId, String toAccountId, double amount, String description){
        // 1- check if from account id exists
        // 2- check if to account id exists
        // 3- check amount is valid : non-negative , greater than balance in from account id
        //4- if valid, save it
        return null;
    }
    public TransactionExecutionResponse executeTransaction(String transactionId){
        // check if this transaction has been initiated
        return null;
    }

    public TransactionHistoryListResponse getTransactionsList(String accountId){
        // check on id
        List<Transaction> transactions = transactionRepo.findByUserId(accountId);

        TransactionHistoryListResponse transactionHistoryListResponse = new TransactionHistoryListResponse();
        transactionHistoryListResponse.setTransaction_response_list(mapper.toTransactionResponseList(transactions));

        return transactionHistoryListResponse;
    }
}
