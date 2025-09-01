package application.feign;

import apis.resources.TransactionDetailList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="TransactionService", url="http://localhost:8080")
public interface TransactionClient {
    @GetMapping("/accounts/{accountId}/transactions")
    public TransactionDetailList getTransactionsList(@PathVariable String accountId);

}
