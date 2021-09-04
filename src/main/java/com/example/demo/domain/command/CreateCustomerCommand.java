package com.example.demo.domain.command;

import java.util.UUID;

import com.example.demo.domain.model.CustomerStatus;
import com.example.demo.infrastructure.dto.CustomerDTO;
//import com.example.demo.domain.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCustomerCommand extends AbstractCommand {

	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private String name;

	public CreateCustomerCommand(final CustomerDTO customer) {
		super(UUID.randomUUID(), CustomerStatus.CREATED);
		this.uuid = this.getId().toString();
		this.name = customer.getName();
	}
}
