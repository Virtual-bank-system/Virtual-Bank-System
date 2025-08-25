package com.Ejada.TransactionService.application.feign;


import com.Ejada.TransactionService.application.feign.dto.AccountDetail;
import com.Ejada.TransactionService.application.feign.dto.AccountTransferRequest;
import com.Ejada.TransactionService.application.feign.dto.AccountTransferResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Will change it
@FeignClient(value = "AccountService", url = "http://localhost:8081")
public interface AccountClient {

    @GetMapping("/accounts/{accountId}")
    AccountDetail getAccountById(@Valid @PathVariable String accountId);

    @PutMapping("/accounts/transfer")
    AccountTransferResponse transfer(@Valid @RequestBody AccountTransferRequest request);
}
