package apis.resources.outResources;

import application.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private String transactionId;
    private Status status;
    private LocalDateTime timestamp;
}
