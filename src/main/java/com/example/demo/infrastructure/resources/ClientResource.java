package com.example.demo.infrastructure.resources;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.ws.rs.core.Response;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.command.CreateClientCommand;
import com.example.demo.infrastructure.dto.ClientDTO;
import com.example.demo.infrastructure.query.model.Client;
import com.example.demo.infrastructure.query.queries.AllClientsQuery;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientResource {

	private QueryGateway queryGateway;
	private CommandGateway commandGateway;

	@PostMapping
	public CompletableFuture<String> execute(@RequestBody ClientDTO body) {
		return commandGateway.send(new CreateClientCommand(UUID.randomUUID(), body.getName()));
	}

	@GetMapping
	public Response execute() {
		return queryGateway
				.query(new AllClientsQuery(), Client.class)
				.thenApply(result -> Response.ok(result).build())
				.join();
	}

	@GetMapping(path = "/domain")
	public Future<List<ClientDTO>> getAllClientsDomain() {
		return queryGateway.query(new ClientDTO(), ResponseTypes.multipleInstancesOf(ClientDTO.class));
	}
}
