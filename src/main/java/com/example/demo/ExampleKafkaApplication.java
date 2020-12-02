package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(excludeName =  {"org.axonframework.extensions.kafka.autoconfig.KafkaAutoConfiguration"})
@SpringBootApplication
public class ExampleKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleKafkaApplication.class, args);
	}

}
