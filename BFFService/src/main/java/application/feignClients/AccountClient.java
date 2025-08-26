package application.feignClients;

import apis.dto.AccountDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// will change it
@FeignClient(name = "AccountService", url = "http://localhost:8081")
public interface AccountClient {

    @GetMapping("accounts/users/{userId}")
    List<AccountDetail> getAccountsByUser(@PathVariable String userId);
}
