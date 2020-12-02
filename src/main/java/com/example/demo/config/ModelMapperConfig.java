package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
	    return new ObjectMapper()
	            .setDefaultPropertyInclusion(Include.NON_NULL)
	            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
	            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
	            .findAndRegisterModules();
	}
}
