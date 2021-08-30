package com.example.demo.infrastructure.query.projections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.event.ClientCreatedEvent;
import com.example.demo.infrastructure.exceptions.ClientException;
import com.example.demo.infrastructure.query.model.Client;
import com.example.demo.infrastructure.query.queries.AllClientsQuery;
import com.example.demo.infrastructure.repository.ClientReadRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ProcessingGroup("kafka-group")
@RequiredArgsConstructor
public class ClientReadDBProjection {

	@Autowired
	private ClientReadRepository repository;

	@EventHandler
	public void on(final ClientCreatedEvent event) throws ClientException {
		log.info("Handling: {} CreatedEvent: {}", event.getClass().getSimpleName(), event);
		try {
			log.info("===== Gravando na Base de leitura ... =====");
			final Client client = Client
					.builder()
					.uuid(event.getId().toString())
					.name(event.getName())
					.createdAt(LocalDate.now().toString())
					.build();
			repository.save(client);
		} catch (final Exception e) {
			throw new ClientException("Ocorreu um erro ao tentar salvar o registro!", e);
		}
		log.info("===== Gravado com sucesso !!! =====");
	}

	@QueryHandler
	public List<Client> handle(final AllClientsQuery clientsQuery) throws ClientException {
		log.info("Handling: {} AllClientsQuery: {}", clientsQuery.getClass().getSimpleName(), clientsQuery);
		try {
			log.info("===== Buscando registros na Base de leitura ... =====");
			final List<Client> clientsFound = repository.findAll();
			if (!clientsFound.isEmpty() && clientsFound != null)
				return clientsFound;
		} catch (final Exception e) {
			throw new ClientException("Ocorreu um erro ao tentar buscar os dados!", e);
		}
		return new ArrayList<>();
	}
}
