package com.Ejada.TransactionService.application.feign;


import org.springframework.cloud.openfeign.FeignClient;

// Will change it
@FeignClient("account-service")
public interface AccountClient {
    // get account details
}
