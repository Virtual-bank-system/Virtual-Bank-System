package application.feignClients;

import apis.dto.AccountDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "AccountService", url = "http://localhost:8084")
public interface AccountClient {

    @GetMapping("accounts/users/{userId}")
    List<AccountDetail> getAccountsByUser(@PathVariable String userId);
}
