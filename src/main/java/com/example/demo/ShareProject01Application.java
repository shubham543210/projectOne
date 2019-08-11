package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * Project01 is a project written from scratch which 
 * provides an idea to fetch real-time data,store 
 * it and use different algorithm over this stored data.
 * Please note that this project will have bugs till i completely sanitize it
 * I will do it once phase 5 is done.I have plans to structure it in phase 3 and 4
 * sorry for inconvenience.
 * All feedbacks are welcomed open heartedly :-). Please create a separate branch in 
 * git if you are pushing any changes.
 * Thanks happy codeing :-)
 * @author Shubham Jaiswal
*/

//Enables Spring's scheduled task execution capability.
@SpringBootApplication
@EnableScheduling
public class ShareProject01Application {

	private static final Logger log = LoggerFactory.getLogger(ShareProject01Application.class);

	public static void main(String[] args) {
		SpringApplication.run(ShareProject01Application.class, args);
	}

}