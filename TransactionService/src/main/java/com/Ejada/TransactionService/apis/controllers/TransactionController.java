package com.Ejada.TransactionService.apis.controllers;

import com.Ejada.TransactionService.apis.resources.inResources.TransferRequestExecution;
import com.Ejada.TransactionService.apis.resources.inResources.TransferRequestInitiation;
import com.Ejada.TransactionService.apis.resources.outResources.TransactionDetailList;
import com.Ejada.TransactionService.apis.resources.outResources.TransferResponse;
import com.Ejada.TransactionService.application.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

   @PostMapping("transactions/transfer/initiation")
    public ResponseEntity<TransferResponse> initiateTransaction(@Valid @RequestBody TransferRequestInitiation request) {
       TransferResponse response = transactionService.initiateTransaction(
               request.getFromAccountId(),
               request.getToAccountId(),
               request.getAmount(),
               request.getDescription()
       );
       return ResponseEntity.ok(response);
    }

    @PostMapping("transactions/transfer/execution")
    public ResponseEntity<TransferResponse> executeTransaction(@Valid @RequestBody TransferRequestExecution request) {
        TransferResponse response = transactionService.executeTransaction(request.getTransactionId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("accounts/{accountId}/transactions")
    public ResponseEntity<TransactionDetailList> getTransactionsList(@Valid @PathVariable String accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsList(accountId));
    }

}
