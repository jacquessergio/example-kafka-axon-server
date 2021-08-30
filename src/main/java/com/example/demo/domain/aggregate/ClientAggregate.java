package com.example.demo.domain.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import com.example.demo.domain.command.CreateClientCommand;
import com.example.demo.domain.event.ClientCreatedEvent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aggregate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientAggregate {

	@AggregateIdentifier
	private UUID id;

	private String name;

	@CommandHandler
	public ClientAggregate(CreateClientCommand command) {
		log.info("Handling {} command: {}", command.getClass().getSimpleName(), command);
		apply(new ClientCreatedEvent(command.getId(), command.getName()));
	}

	@EventHandler
	public void on(ClientCreatedEvent event) {
		log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
		this.id = event.getId();
		this.name = event.getName();
	}

}
