package application.feignClients;

import apis.dto.TransactionDetailList;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "TransactionService", url = "http://localhost:8080")
public interface TransactionClient {

    @GetMapping("/accounts/{accountId}/transactions")
    TransactionDetailList getTransactionsList(@PathVariable String accountId);
}
