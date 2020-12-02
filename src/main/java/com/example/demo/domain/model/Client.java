package com.example.demo.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Document
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

	@Id
	private String id;
	
	private String name;
}
