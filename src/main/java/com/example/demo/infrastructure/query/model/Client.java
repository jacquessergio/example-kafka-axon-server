package com.example.demo.infrastructure.query.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@Document
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {

	@Id
	private String id;

	@Field(name = "uuid")
	private UUID uuid;

	@Field(name = "name")
	private String name;

	@Field(name = "createdAt")
	private String createdAt;

}
