package com.example.demo.infrastructure.dto;

import java.io.Serializable;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.example.demo.domain.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@TargetAggregateIdentifier
	private String id;

	private String uuid;

	private String name;

	public CustomerDTO(Customer client) {
		this.id = String.valueOf(client.getId());
		this.uuid = client.getUuid();
		this.name = client.getName();
	}
}
