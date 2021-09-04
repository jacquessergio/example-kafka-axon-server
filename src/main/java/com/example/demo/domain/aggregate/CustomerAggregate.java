package com.example.demo.domain.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.command.CreateCustomerCommand;
import com.example.demo.domain.command.DeleteCustomerCommand;
import com.example.demo.domain.command.UpdateCustomerCommand;
import com.example.demo.domain.event.CustomerCreatedEvent;
import com.example.demo.domain.event.CustomerDeletedEvent;
import com.example.demo.domain.event.CustomerUpdatedEvent;
import com.example.demo.domain.model.CustomerStatus;
import com.example.demo.infrastructure.exceptions.CustomerException;
import com.example.demo.infrastructure.service.CustomerService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aggregate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerAggregate {

	@AggregateIdentifier
	private UUID id;
	
	private String uuid;

	private String name;

	private CustomerStatus status;

	@CommandHandler
	public CustomerAggregate(final CreateCustomerCommand command, @Autowired CustomerService service)
			throws CustomerException {
		log.info("Handling {} command: {}", command.getClass().getSimpleName(), command);

		try {
			service.createCustomer(command);
		} catch (final Exception e) {
			log.error(">> {}", e.getLocalizedMessage());
			command.setStatus(CustomerStatus.ERROR);
		} finally {
			apply(new CustomerCreatedEvent(command));
		}
	}

	@CommandHandler
	public CustomerAggregate(final UpdateCustomerCommand command, @Autowired CustomerService service)
			throws CustomerException {
		log.info("Handling {} command: {}", command.getClass().getSimpleName(), command);

		try {
			service.updateCustomer(command);
		} catch (final Exception e) {
			log.error(">> {}", e.getLocalizedMessage());
			command.setStatus(CustomerStatus.ERROR);
		} finally {
			apply(new CustomerUpdatedEvent(command));
		}
	}

	@CommandHandler
	public CustomerAggregate(final DeleteCustomerCommand command, @Autowired CustomerService service)
			throws CustomerException {
		log.info("Handling {} command: {}", command.getClass().getSimpleName(), command);

		try {
			service.deleteCustomerByUUID(command.getUuid());
		} catch (final Exception e) {
			log.error(">> {}", e.getLocalizedMessage());
			command.setStatus(CustomerStatus.ERROR);
		} finally {
			apply(new CustomerDeletedEvent(command));
		}
	}

	@EventSourcingHandler
	protected void on(CustomerCreatedEvent event) {
		log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
		this.id = event.getId();
		this.uuid = event.getUuid();
		this.name = event.getName();
		this.status = event.getStatus();
	}

	@EventSourcingHandler
	protected void on(CustomerUpdatedEvent event) {
		log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
		this.id = event.getId();
		this.uuid = event.getUuid();
		this.name = event.getName();
		this.status = event.getStatus();
	}

	@EventSourcingHandler
	protected void on(CustomerDeletedEvent event) {
		log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
		this.id = event.getId();
		this.uuid = event.getUuid();
		this.status = event.getStatus();
	}

}
