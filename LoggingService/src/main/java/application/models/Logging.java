package application.models;

import application.enums.MessageType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
public class Logging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType message_type;

    @Column(nullable = false)
    private LocalDateTime date_time;
}
