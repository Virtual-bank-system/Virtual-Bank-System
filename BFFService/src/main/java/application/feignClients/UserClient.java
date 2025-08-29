package application.feignClients;

import apis.resources.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "UserService", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping("users/{userId}")
    UserProfile getProfile(@PathVariable String userId);
}
