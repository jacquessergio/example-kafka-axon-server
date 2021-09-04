package com.example.demo.infrastructure.query.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.demo.domain.event.CustomerCreatedEvent;
import com.example.demo.domain.event.CustomerUpdatedEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@Document
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Field(name = "uuid")
	private UUID uuid;

	@Field(name = "name")
	private String name;

	@Field(name = "createdAt")
	private String createdAt;

	public Customer(final CustomerCreatedEvent event) {
		this.uuid = event.getId();
		this.name = event.getName();
		this.createdAt = LocalDate.now().toString();
	}
	public Customer(final String id, final CustomerUpdatedEvent event) {
		this.id = id;
		this.uuid = event.getId();
		this.name = event.getName();
		this.createdAt = LocalDate.now().toString();
	}
}
