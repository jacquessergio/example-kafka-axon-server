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
import com.example.demo.infrastructure.dto.ClientDTO;
import com.example.demo.infrastructure.exceptions.ClientException;
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

	@EventHandler
	public void on(ClientCreatedEvent event) throws ClientException {
		log.info("Handling: {} CreatedEvent: {}", event.getClass().getSimpleName(), event);
		try {
			log.info("===== Gravando na Base de Dominio ... =====");
			final Client client =  Client
					.builder()
					.uuid(event.getId().toString())
					.name(event.getName())
					.build();
			final var clientSaved = repository.save(client);
			log.info("===== [DBWrite] {} gravado com sucesso !!! =====", clientSaved);
		} catch (final Exception e) {
			throw new ClientException("Ocorreu um erro ao tentar salvar o registro!", e);
		}
	}

	@QueryHandler
	public List<ClientDTO> handle(ClientDTO dto) throws ClientException {
		log.info("Handling: {} ClientDTO: {}", dto.getClass().getSimpleName(), dto);
		try {
			log.info("===== Buscando registros na Base de dominio ... =====");
			final List<Client> clientsFound = repository.findAll();
			if (!clientsFound.isEmpty() && clientsFound != null)
				return Lists.transform(clientsFound, client -> new ClientDTO(client));
		} catch (final Exception e) {
			throw new ClientException("Ocorreu um erro ao tentar buscar os dados!", e);
		}
		return new ArrayList<>();
	}
}
