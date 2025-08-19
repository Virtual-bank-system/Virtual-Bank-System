package com.Ejada.TransactionService.application.repos;

import com.Ejada.TransactionService.application.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction,String> {
}
