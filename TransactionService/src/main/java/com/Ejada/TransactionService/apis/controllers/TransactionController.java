package com.Ejada.TransactionService.apis.controllers;

import com.Ejada.TransactionService.apis.resources.inResources.ExecuteTransactionRequest;
import com.Ejada.TransactionService.apis.resources.inResources.InitiateTransactionRequest;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionExecutionResponse;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionHistoryListResponse;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionInitiationResponse;
import com.Ejada.TransactionService.application.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

   @PostMapping("/transfer/initiation")
    public ResponseEntity<TransactionInitiationResponse> initiateTransaction(@RequestBody InitiateTransactionRequest request) {
       TransactionInitiationResponse response = transactionService.initiateTransaction(
               request.getFrom_account_id(),
               request.getTo_account_id(),
               request.getAmount(),
               request.getDescription()
       );
       return ResponseEntity.ok(response);
    }

    @PostMapping("/transfer/execution")
    public ResponseEntity<TransactionExecutionResponse> executeTransaction(@RequestBody ExecuteTransactionRequest request) {
        TransactionExecutionResponse response = transactionService.executeTransaction(request.getTransaction_id());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<TransactionHistoryListResponse> getTransactionsList(@PathVariable String account_id) {
        return ResponseEntity.ok(transactionService.getTransactionsList(account_id));
    }
}
