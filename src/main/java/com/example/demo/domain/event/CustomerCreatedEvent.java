package com.example.demo.domain.event;

import com.example.demo.domain.command.CreateCustomerCommand;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCreatedEvent extends AbstractEvent {

	private static final long serialVersionUID = 1L;

	private String uuid;
	private String name;

	public CustomerCreatedEvent(CreateCustomerCommand command) {
		super(command.getId(), command.getStatus());
		this.uuid = command.getUuid();
		this.name = command.getName();
	}
}
