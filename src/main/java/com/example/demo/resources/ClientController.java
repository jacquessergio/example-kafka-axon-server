package com.example.demo.resources;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.commons.command.ClientCommand;
import com.example.demo.domain.dto.ClientDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/teste")
@AllArgsConstructor
public class ClientController {

	private CommandGateway commandGateway;

	@PostMapping
	public CompletableFuture<String> execute(@RequestBody ClientDTO body) {
		return commandGateway.send(new ClientCommand(UUID.randomUUID(), body));
	}
}
