package com.example.demo.infrastructure.query.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

	@Field(name = "uuid")
	private String uuid;

	@Field(name = "name")
	private String name;

	@Field(name = "createdAt")
	private String createdAt;

}
