package com.project.MonthlyMessApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MonthlyMessAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonthlyMessAppApplication.class, args);
	}

}
