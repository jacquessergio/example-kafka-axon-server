package com.example.demo.domain.event;

import com.example.demo.domain.command.DeleteCustomerCommand;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDeletedEvent extends AbstractEvent {

	private static final long serialVersionUID = 1L;
	private String uuid;

	public CustomerDeletedEvent(DeleteCustomerCommand command) {
		super(command.getId(), command.getStatus());
		this.uuid = command.getUuid();
	}
}
