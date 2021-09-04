package com.example.demo.domain.command;

import java.util.UUID;

import com.example.demo.domain.model.CustomerStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteCustomerCommand extends AbstractCommand {

	private static final long serialVersionUID = 1L;
	private String uuid;

	public DeleteCustomerCommand(final String uuid) {
		super(UUID.randomUUID(), CustomerStatus.DELETED);
		this.uuid = uuid;
	}
}
