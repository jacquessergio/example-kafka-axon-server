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
public class UpdateCustomerCommand extends AbstractCommand {

	private static final long serialVersionUID = 1L;

	private String name;
	private String uuid;

	public UpdateCustomerCommand(final CustomerDTO customer) {
		super(UUID.randomUUID(), CustomerStatus.UPDATED);
		this.uuid = customer.getUuid();
		this.name = customer.getName();
	}

	public UpdateCustomerCommand(final String id, final CustomerDTO customer) {
		super(UUID.randomUUID(), CustomerStatus.UPDATED);
		this.uuid = id;
		this.name = customer.getName();
	}
}
