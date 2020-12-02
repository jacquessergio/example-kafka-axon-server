package com.example.demo.infrastructure.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.commons.event.ClientCreatedEvent;
import com.example.demo.commons.mappers.ClientModelMapper;
import com.example.demo.domain.dto.ClientDTO;
import com.example.demo.domain.model.Client;
import com.example.demo.infrastructure.repository.ClientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ProcessingGroup("kafka-group")
@RequiredArgsConstructor
public class ClientProjection {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private ClientModelMapper<ClientDTO, Client> clientModelMapper;

	@EventHandler
	public void on(ClientCreatedEvent event) {
		log.info("Handling: {} event: {}", event.getClass().getSimpleName(), event);
		repository.save(toModel(event.getClient()));
	}

	private Client toModel(final ClientDTO clientDto) {
		return clientModelMapper.convertDtoToModel(clientDto, Client.class);
	}
}
