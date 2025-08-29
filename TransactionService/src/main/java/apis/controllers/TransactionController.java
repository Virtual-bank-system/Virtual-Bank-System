package apis.controllers;

import apis.resources.inResources.TransferRequestExecution;
import apis.resources.inResources.TransferRequestInitiation;
import apis.resources.outResources.TransactionDetailList;
import apis.resources.outResources.TransferResponse;
import application.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

   @PostMapping("/transactions/transfer/initiation")
    public ResponseEntity<TransferResponse> initiateTransaction(@Valid @RequestBody TransferRequestInitiation request) {
       TransferResponse response = transactionService.initiateTransaction(
               request.getFromAccountId(),
               request.getToAccountId(),
               request.getAmount(),
               request.getDescription()
       );
       System.out.println(request);
       return ResponseEntity.ok(response);
    }

    @PostMapping("/transactions/transfer/execution")
    public ResponseEntity<TransferResponse> executeTransaction(@Valid @RequestBody TransferRequestExecution request) {
        TransferResponse response = transactionService.executeTransaction(request.getTransactionId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<TransactionDetailList> getTransactionsList(@PathVariable String accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsList(accountId));
    }

}
