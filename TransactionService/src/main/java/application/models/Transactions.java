package application.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import application.enums.Status;


@Data
@Entity
@Table(name = "transaction")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    @Column(nullable = false)
    private String fromAccountId;

    @Column(nullable = false)
    private String toAccountId;

    @Column(columnDefinition = "DECIMAL(15,2)", nullable = false)
    private double amount;

    @Column(length = 255, nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'INITIATED'")
    private Status status = Status.INITIATED;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp = LocalDateTime.now();
}
