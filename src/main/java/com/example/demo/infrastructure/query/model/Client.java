package com.example.demo.infrastructure.query.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.demo.domain.event.ClientCreatedEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@Document
@SuperBuilder
@AllArgsConstructor
@ToString
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Field(name = "uuid")
	private UUID uuid;

	@Field(name = "name")
	private String name;

	@Field(name = "createdAt")
	private String createdAt;

	public Client(final ClientCreatedEvent event) {
		this.uuid = event.getId();
		this.name = event.getName();
		this.createdAt = LocalDate.now().toString();
	}
}
