package application.repos;

import application.models.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepo extends JpaRepository<Logs, Long> {
}
