package com.Ejada.TransactionService.application.feign;

import org.springframework.cloud.openfeign.FeignClient;

// Will change it
@FeignClient("user-service")
public interface UserClient {
    // get user details
}
