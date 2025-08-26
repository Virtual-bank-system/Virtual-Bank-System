package com.example.demo.application.repos;

import com.example.demo.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
