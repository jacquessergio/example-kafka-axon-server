package com.example.demo.infrastructure.dto;

import java.io.Serializable;

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
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@TargetAggregateIdentifier
	private String id;

	private String uuid;

	private String name;

	public ClientDTO(Client client) {
		this.id = String.valueOf(client.getId());
		this.uuid = client.getUuid();
		this.name = client.getName();
	}
}
