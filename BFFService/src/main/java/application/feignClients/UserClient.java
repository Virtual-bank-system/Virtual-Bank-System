package application.feignClients;

import apis.dto.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// will change it
@FeignClient(name = "UserService", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping("users/{userId}")
    public UserProfile getProfile(@PathVariable String userId);
}
