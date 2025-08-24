package com.Ejada.TransactionService.application.feign;


import com.Ejada.TransactionService.application.feign.dto.AccountDetail;
import com.Ejada.TransactionService.application.feign.dto.AccountTransferRequest;
import com.Ejada.TransactionService.application.feign.dto.AccountTransferResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Will change it
@FeignClient("AccountService")
@RequestMapping("/accounts")
public interface AccountClient {

    @GetMapping("/{accountId}")
    ResponseEntity<AccountDetail> getAccountById(@PathVariable String accountId);

    @PutMapping("/transfer")
    ResponseEntity<AccountTransferResponse> transfer(@Valid @RequestBody AccountTransferRequest request);
}
