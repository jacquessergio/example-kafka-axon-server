package com.example.demo.commons.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.example.demo.domain.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ClientCommand {

	@TargetAggregateIdentifier
	private UUID id;

	private ClientDTO client;
}
