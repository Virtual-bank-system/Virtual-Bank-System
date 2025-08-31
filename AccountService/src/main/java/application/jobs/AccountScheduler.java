package application.jobs;

import application.enums.Status;
import application.models.Account;
import application.repos.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AccountScheduler {

    @Autowired
    private AccountRepo accountRepo;

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void markInactiveAccounts() {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(24);

        List<Account> accounts = accountRepo.findAll().stream()
                .filter(acc -> acc.getStatus() == Status.ACTIVE)
                .filter(acc -> acc.getUpdated_at().isBefore(cutoff))
                .toList();

        accounts.forEach(acc -> {
            acc.setStatus(Status.INACTIVE);
            acc.setUpdated_at(LocalDateTime.now());
            accountRepo.save(acc);
        });
    }
}



