package com.example.demo.infrastructure.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.infrastructure.dto.CustomerDTO;
import com.example.demo.infrastructure.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customers-domain")
@AllArgsConstructor
public class CustomerDomainResource {

	private CustomerService service;

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAllCustomerDomain() {

		try {
			final List<CustomerDTO> customers = service.findAllCustomers();
			return new ResponseEntity<>(customers, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
