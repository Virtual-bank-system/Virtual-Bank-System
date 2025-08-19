package com.Ejada.TransactionService.application.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import com.Ejada.TransactionService.application.enums.Status;


@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String from_account_id;

    @Column(nullable = false)
    private String to_account_id;

    @Column(columnDefinition = "DECIMAL(15,2)", nullable = false)
    private double amount;

    @Column(length = 255, nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "DEFAULT 'INITIATED'")
    private Status status;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime timestamp;
}
