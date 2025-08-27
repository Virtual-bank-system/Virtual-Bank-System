package com.example.demo.application.repos;

import com.example.demo.application.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account,String> {
    boolean existsByAccountNumber(String accountNumber);
}
