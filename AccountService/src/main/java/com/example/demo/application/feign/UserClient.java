package com.example.demo.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="UserService", url="http://localhost:9090")
public interface UserClient {

    @GetMapping("/users/{userID}")
    ResponseEntity<UserProfile> getProfile(@PathVariable String userID);
}
