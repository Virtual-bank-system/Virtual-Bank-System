package application.jobs;

import application.enums.Status;
import application.feign.TransactionDetail;
import application.feign.TransactionDetailList;
import application.models.Account;
import application.repos.AccountRepo;
import application.feign.TransactionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
public class AccountScheduler {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionClient transactionClient;

    @Scheduled(cron = "0 */1 * ? * *") 
    public void markInactiveAccounts() {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(24);

        List<Account> accounts = accountRepo.findAll().stream()
                .filter(acc -> acc.getStatus() == Status.ACTIVE)
                .toList();

        accounts.forEach(acc -> {
            try {
                    TransactionDetailList txList = transactionClient.getTransactionsList(acc.getId());

                    LocalDateTime lastTxTime = txList.getTransactionDetailList().stream()
                            .map(TransactionDetail::getTimestamp)
                            .max(Comparator.naturalOrder())
                            .orElse(null);

                    boolean lastTxOld = lastTxTime.isBefore(cutoff);

                    if (lastTxOld) {
                        acc.setStatus(Status.INACTIVE);
                        acc.setUpdated_at(LocalDateTime.now());
                        accountRepo.save(acc);
                    }
            }
            catch(Exception e){
                acc.setStatus(Status.INACTIVE);
                acc.setUpdated_at(LocalDateTime.now());
                accountRepo.save(acc);
            }
        });
    }
}
