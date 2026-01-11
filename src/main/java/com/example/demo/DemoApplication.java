package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);

        SpringApplication app = new SpringApplication(DemoApplication.class);
        app.addListeners((ApplicationEnvironmentPreparedEvent event) -> {
            var env = event.getEnvironment();
            System.out.println("=== EARLY ENV CHECK ===");
            System.out.println("spring.datasource.url=" + env.getProperty("spring.datasource.url"));
            System.out.println("spring.datasource.username=" + env.getProperty("spring.datasource.username"));
            System.out.println("spring.datasource.password=" + env.getProperty("spring.datasource.password"));
            System.out.println("spring.profiles.active=" + env.getProperty("spring.profiles.active"));
            System.out.println("=======================");
        });

        app.run(args);
	}



}
