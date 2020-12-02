package com.example.demo.commons.event;

import java.util.UUID;

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
public class ClientCreatedEvent {

	private UUID id;

	private ClientDTO client;
}
