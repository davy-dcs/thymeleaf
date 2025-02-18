package com.insy2s.thymeleaf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ThymleafTemplate.genrate();
	}
}
