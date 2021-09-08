package com.example.demo.domain.event;

import java.io.Serializable;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.example.demo.domain.model.CustomerStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	@TargetAggregateIdentifier
	private UUID id;

	private CustomerStatus status;

}
