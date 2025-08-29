package application.repos;

import application.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,String> {
    List<Transaction> findByFromAccountId(String fromAccountId);
    List<Transaction> findByToAccountId(String toAccountId);
}
