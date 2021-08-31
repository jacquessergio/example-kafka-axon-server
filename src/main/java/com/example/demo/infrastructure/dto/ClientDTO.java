package com.example.demo.infrastructure.dto;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.example.demo.domain.model.Client;
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
public class ClientDTO {

	@TargetAggregateIdentifier
	private String id;

	private UUID uuid;

	private String name;

	public ClientDTO(Client client) {
		this.id = String.valueOf(client.getId());
		this.uuid = client.getUuid();
		this.name = client.getName();
	}
}
