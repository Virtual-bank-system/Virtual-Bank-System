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
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

   @PutMapping("/transfer/initiation")
    public ResponseEntity<TransferResponse> initiateTransaction(@Valid @RequestBody TransferRequestInitiation request) {
       TransferResponse response = transactionService.initiateTransaction(
               request.getFrom_account_id(),
               request.getTo_account_id(),
               request.getAmount(),
               request.getDescription()
       );
       return ResponseEntity.ok(response);
    }

    @PutMapping("/transfer/execution")
    public ResponseEntity<TransferResponse> executeTransaction(@Valid @RequestBody TransferRequestExecution request) {
        TransferResponse response = transactionService.executeTransaction(request.getTransaction_id());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<TransactionDetailList> getTransactionsList(@Valid @PathVariable String accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsList(accountId));
    }

}
