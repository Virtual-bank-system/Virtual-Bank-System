package application.repos;

import application.models.Logging;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface LoggingRepo extends JpaRepository<Logging, BigInteger> {
}
