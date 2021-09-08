package com.example.demo.domain.event;

import com.example.demo.domain.command.UpdateCustomerCommand;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerUpdatedEvent extends AbstractEvent {

	private static final long serialVersionUID = 1L;

	private String uuid;
	private String name;

	public CustomerUpdatedEvent(UpdateCustomerCommand command) {
		super(command.getId(), command.getStatus());
		this.name = command.getName();
		this.uuid = command.getUuid();
	}
}
