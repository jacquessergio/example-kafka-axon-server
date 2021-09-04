package com.example.demo.infrastructure.resources;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.command.CreateCustomerCommand;
import com.example.demo.domain.command.DeleteCustomerCommand;
import com.example.demo.domain.command.UpdateCustomerCommand;
import com.example.demo.infrastructure.dto.CustomerDTO;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerCommandResource {

	private CommandGateway commandGateway;

	@PostMapping("/customers")
	public CompletableFuture<String> createCustomer(@RequestBody CustomerDTO body) {
		return commandGateway.send(new CreateCustomerCommand(body));
	}

	@PutMapping("/customers/{id}")
	public CompletableFuture<String> updateCustomer(@PathVariable(name = "id") String id, @RequestBody CustomerDTO body) {
		return commandGateway.send(new UpdateCustomerCommand(id, body));
	}

	@DeleteMapping("/customers/{id}")
	public CompletableFuture<String> deleteCustomer(@PathVariable(name = "id") String id) {
		return commandGateway.send(new DeleteCustomerCommand(id));
	}
}
