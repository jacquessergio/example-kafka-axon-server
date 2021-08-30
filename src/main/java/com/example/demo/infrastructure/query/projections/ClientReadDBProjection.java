package com.example.demo.infrastructure.query.projections;

import java.time.LocalDate;
import java.util.List;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.event.ClientCreatedEvent;
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
	public void on(ClientCreatedEvent event) {
		log.info("Handling: {} CreatedEvent: {}", event.getClass().getSimpleName(), event);
		System.out.println("===== gravando base leitura =====");
		
		final Client client = Client
				.builder()
				.uuid(event.getId().toString())
				.name(event.getName())
				.createdAt(LocalDate.now().toString())
				.build();
		
		repository.save(client);
	}

	@QueryHandler
	public List<Client> handle(AllClientsQuery clientsQuery) {
		return repository.findAll();
	}
}
