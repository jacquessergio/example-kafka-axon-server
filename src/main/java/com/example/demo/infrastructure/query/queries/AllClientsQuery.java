package com.example.demo.infrastructure.query.queries;

import java.io.Serializable;
import java.util.List;

import com.example.demo.infrastructure.query.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllClientsQuery implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Customer> clients;
}
