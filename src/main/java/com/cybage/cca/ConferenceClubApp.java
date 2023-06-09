package com.cybage.cca;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.cybage.repository")
@EntityScan(basePackages = "com.cybage.model")
@SpringBootApplication(scanBasePackages = "com.cybage")
   
   public class ConferenceClubApp{
   public static void main(String[] args) {
   SpringApplication.run(ConferenceClubApp.class, args);
	}}
   



