package com.example.demo.infrastructure.query.projections;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.event.ClientCreatedEvent;
import com.example.demo.domain.model.Client;
import com.example.demo.infrastructure.commons.mappers.ClientModelMapper;
import com.example.demo.infrastructure.dto.ClientDTO;
import com.example.demo.infrastructure.repository.ClientWriteRepository;
import com.google.common.collect.Lists;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ProcessingGroup("kafka-group")
@RequiredArgsConstructor
public class ClientWriteDBProjection {

	@Autowired
	private ClientWriteRepository repository;

	@Autowired
	private ClientModelMapper<ClientDTO, Client> clientModelMapper;

	@EventHandler
	public void on(ClientCreatedEvent event) {
		log.info("Handling: {} CreatedEvent: {}", event.getClass().getSimpleName(), event);

		log.info("===== gravando base escrita =====");

		final Client client = Client.builder().uuid(event.getId().toString()).name(event.getName()).build();

		repository.save(client);
	}

	@QueryHandler
	public List<ClientDTO> handle(ClientDTO dto) {
		final List<Client> clientsFound = repository.findAll();
		if (!clientsFound.isEmpty() && clientsFound != null)
			return Lists.transform(clientsFound, client -> new ClientDTO(client));
		return new ArrayList<>();
	}

	private Client toModel(final ClientDTO clientDto) {
		return clientModelMapper.convertDtoToModel(clientDto, Client.class);
	}
}
