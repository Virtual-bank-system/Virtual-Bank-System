package application.repos;

import application.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transactions,String> {
    List<Transactions> findByFromAccountId(String fromAccountId);
    List<Transactions> findByToAccountId(String toAccountId);
}
