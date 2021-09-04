package com.example.demo.infrastructure.service;

import java.util.List;

import com.example.demo.domain.command.CreateCustomerCommand;
import com.example.demo.domain.command.UpdateCustomerCommand;
import com.example.demo.domain.model.Customer;
import com.example.demo.infrastructure.dto.CustomerDTO;
import com.example.demo.infrastructure.exceptions.CustomerException;

public interface CustomerService {

	public void createCustomer(CreateCustomerCommand command) throws CustomerException;

	public void updateCustomer(UpdateCustomerCommand command) throws CustomerException;

	public void deleteCustomerByUUID(String uuid) throws CustomerException;

	public Customer findCustomerByUUID(String uuid) throws CustomerException;

	public List<CustomerDTO> findAllCustomers() throws CustomerException;
}
