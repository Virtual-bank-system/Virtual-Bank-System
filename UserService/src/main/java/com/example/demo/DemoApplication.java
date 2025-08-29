package com.example.demo;

import apis.resources.LogMessage;
import application.enums.MessageType;
import application.services.KafkaProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;

@SpringBootApplication
@ComponentScan(basePackages = {"application","apis","com"})
@EnableJpaRepositories(basePackages = "application.repos")
@EntityScan(basePackages = "application.models")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
