package application.feignClients;


import apis.dto.AccountDetail;
import apis.dto.AccountTransferRequest;
import apis.dto.AccountTransferResponse;
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
