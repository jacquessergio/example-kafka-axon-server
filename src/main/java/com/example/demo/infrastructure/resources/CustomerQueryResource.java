package com.example.demo.infrastructure.resources;

import java.util.List;
import java.util.concurrent.Future;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.infrastructure.query.model.Customer;
import com.example.demo.infrastructure.query.queries.AllClientsQuery;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerQueryResource {

	private QueryGateway queryGateway;

	@GetMapping("/customers")
	public Future<List<Customer>> findAllCustomers() {
		return queryGateway.query(new AllClientsQuery(), ResponseTypes.multipleInstancesOf(Customer.class));
	}
}
