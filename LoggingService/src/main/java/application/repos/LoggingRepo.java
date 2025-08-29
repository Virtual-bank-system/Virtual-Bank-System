package application.repos;

import application.models.Logging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface LoggingRepo extends JpaRepository<Logging, Long> {
}
