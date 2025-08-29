package com.Ejada.LoggingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication(
		scanBasePackages = {"com.Ejada.LoggingService", "application", "apis"}
)
@EnableJpaRepositories(basePackages = "application.repos")
@EntityScan(basePackages = "application.models")
@EnableKafka
public class LoggingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingServiceApplication.class, args);
	}

}
