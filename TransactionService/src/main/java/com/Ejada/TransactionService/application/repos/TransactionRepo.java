package com.Ejada.TransactionService.application.repos;

import com.Ejada.TransactionService.application.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction,String> {
    List<Transaction> findByFromAccountId(String fromAccountId);
    List<Transaction> findByToAccountId(String toAccountId);
}
