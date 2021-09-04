package com.example.demo.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.domain.command.CreateCustomerCommand;
import com.example.demo.domain.command.UpdateCustomerCommand;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor()
@ToString
@Table(name = "clients")
@EqualsAndHashCode
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "uuid", nullable = false)
	private String uuid;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	public Customer(CreateCustomerCommand command) {
		this.uuid = command.getId().toString();
		this.name = command.getName();
	}
	public Customer(Long customerId, UpdateCustomerCommand command) {
		this.id = customerId;
		this.uuid = command.getUuid();
		this.name = command.getName();
	}

	public boolean hasError() {
		return (this == null) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	
}
