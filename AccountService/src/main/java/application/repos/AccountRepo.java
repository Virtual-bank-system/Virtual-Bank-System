package application.repos;

import application.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,String> {
    boolean existsByAccountNumber(String accountNumber);
}
