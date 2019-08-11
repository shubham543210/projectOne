package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShareProject1Application {

	private static final Logger log = LoggerFactory.getLogger(ShareProject1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(ShareProject1Application.class, args);
	}

}