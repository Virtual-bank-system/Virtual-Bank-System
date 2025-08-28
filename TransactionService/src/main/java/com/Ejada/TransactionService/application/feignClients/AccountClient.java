package com.Ejada.TransactionService.application.feignClients;


import com.Ejada.TransactionService.apis.dto.AccountDetail;
import com.Ejada.TransactionService.apis.dto.AccountTransferRequest;
import com.Ejada.TransactionService.apis.dto.AccountTransferResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "AccountService", url = "http://localhost:8084")
public interface AccountClient {

    @GetMapping("/accounts/{accountId}")
    AccountDetail getAccountById(@Valid @PathVariable String accountId);

    @PutMapping("/accounts/transfer")
    AccountTransferResponse transfer(@Valid @RequestBody AccountTransferRequest request);
}
