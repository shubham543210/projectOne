package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2
public class ShareProject01Application {

	private static final Logger log = LoggerFactory.getLogger(ShareProject01Application.class);

	public static void main(String[] args) {
		SpringApplication.run(ShareProject01Application.class, args);
	}
	
	@Bean
	public Docket productApi() {
	   return new Docket(DocumentationType.SWAGGER_2).select()
	      .apis(RequestHandlerSelectors.basePackage("com.example.demo")).build();
	}

}

//swagger -ui--http://localhost:8080/swagger-ui.html

/*
 * Note-While running the code error message will be shown 
 * in console as config is written to connect to 
 * local mongo.Which I have not removed for testing purpose.
 * No need to be bothered about it as of now.
 */
