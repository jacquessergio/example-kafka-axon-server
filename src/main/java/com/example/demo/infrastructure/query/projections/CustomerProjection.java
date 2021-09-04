package com.example.demo.infrastructure.query.projections;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.event.CustomerCreatedEvent;
import com.example.demo.domain.event.CustomerDeletedEvent;
import com.example.demo.domain.event.CustomerUpdatedEvent;
import com.example.demo.infrastructure.exceptions.CustomerException;
import com.example.demo.infrastructure.query.model.Customer;
import com.example.demo.infrastructure.query.queries.AllClientsQuery;
import com.example.demo.infrastructure.repository.CustomerReadRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ProcessingGroup("kafka-group")
@RequiredArgsConstructor
public class CustomerProjection {

	@Autowired
	private CustomerReadRepository repository;

	@EventHandler
	public void on(final CustomerCreatedEvent event) throws CustomerException {
		log.info("Handling: {} CreatedEvent: {}", event.getClass().getSimpleName(), event);
		try {
			log.info("===== Gravando registro na Base de leitura ... =====");
			final var clientSaved = repository.save(new Customer(event));
			log.info("===== [DBRead] {} gravado com sucesso !!! =====", clientSaved);
		} catch (final Exception e) {
			throw new CustomerException("Ocorreu um erro ao tentar salvar o registro!", e);
		}
	}

	@EventHandler
	public void on(final CustomerUpdatedEvent event) throws CustomerException {
		log.info("Handling: {} UpdatedEvent: {}", event.getClass().getSimpleName(), event);
		try {
			log.info("===== Atualizando registro na Base de leitura ... =====");

			final Customer customerFound = repository.findByUuid(UUID.fromString(event.getUuid()));
			if (customerFound == null) {
				throw new CustomerException("Nenhum registro encontrado!");
			}

			final var clientSaved = repository.save(new Customer(customerFound.getId(), event));
			log.info("===== [DBRead] {} gravado com sucesso !!! =====", clientSaved);
		} catch (final CustomerException e) {
			throw new CustomerException(e.getMessage());
		} catch (final Exception e) {
			throw new RuntimeErrorException(new Error(e), "Ocorreu um erro ao processar a solicitação!");
		}
	}

	@EventHandler
	public void on(final CustomerDeletedEvent event) throws CustomerException {
		log.info("Handling: {} DeletedEvent: {}", event.getClass().getSimpleName(), event);
		try {
			log.info("===== Removendo registro na Base de leitura ... =====");

			final Customer customerFound = repository.findByUuid(UUID.fromString(event.getUuid()));
			if (customerFound == null) {
				throw new CustomerException("Nenhum registro encontrado!");
			}

			repository.delete(customerFound);
			log.info("===== [DBRead] removido com sucesso !!! =====");
		} catch (final CustomerException e) {
			throw new CustomerException(e.getMessage());
		} catch (final Exception e) {
			throw new RuntimeErrorException(new Error(e), "Ocorreu um erro ao processar a solicitação!");
		}
	}

	@QueryHandler
	public List<Customer> handle(final AllClientsQuery clientsQuery) throws CustomerException {
		log.info("Handling: {} AllClientsQuery: {}", clientsQuery.getClass().getSimpleName(), clientsQuery);
		try {
			log.info("===== Buscando registros na Base de leitura ... =====");
			final List<Customer> clientsFound = repository.findAll();
			if (!clientsFound.isEmpty() && clientsFound != null)
				return clientsFound;
		} catch (final Exception e) {
			throw new CustomerException("Ocorreu um erro ao tentar buscar os dados!", e);
		}
		return new ArrayList<>();
	}
}
