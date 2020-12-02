package com.example.demo.commons.command;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.demo.domain.dto.ClientDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LegacyClientCommand {
	
	@Autowired
	private CommandGateway commandGateway;

	@KafkaListener(
			topics = "axon-events-legacy", 
			groupId = "${kafka.group.id}",
			containerFactory = "kafkaListenerContainerFactoryClientDTO"
			)
	void legacy(@Payload ClientDTO command) throws Exception {
		log.info("Legacy: {}", command);
		commandGateway.send(new ClientCommand(UUID.randomUUID(), command));
	}
	
}
