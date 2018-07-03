package com.sample.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		
		System.out.println(System.getProperty("logFileLocation"));
		SpringApplication.run(SpringRestApplication.class, args);
	}
}
