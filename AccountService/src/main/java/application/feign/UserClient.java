package application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="UserService", url="http://localhost:8081")
public interface UserClient {

    @GetMapping("/users/{userID}/profile")
    ResponseEntity<UserProfile> getProfile(@PathVariable String userID);
}
