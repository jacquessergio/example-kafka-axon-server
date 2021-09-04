package com.example.demo.infrastructure.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.domain.command.CreateCustomerCommand;
import com.example.demo.domain.command.UpdateCustomerCommand;
import com.example.demo.domain.model.Customer;
import com.example.demo.infrastructure.dto.CustomerDTO;
import com.example.demo.infrastructure.exceptions.CustomerException;
import com.example.demo.infrastructure.repository.CustomerWriteRepository;
import com.example.demo.infrastructure.service.CustomerService;
import com.google.common.collect.Lists;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerWriteRepository repository;

	@Override
	public void createCustomer(CreateCustomerCommand command) throws CustomerException {

		/**
		 * Here, for example, you can apply business logic
		 */

		try {
			log.info("=====  Gravando na Base de Dominio ... =====");

			final Customer clientSaved = repository.save(new Customer(command));
			if (clientSaved.hasError()) {
				throw new CustomerException("Erro ao tentar salvar usuário!");
			}
			log.info("===== [DBWrite] {} gravado com sucesso !!! =====", clientSaved);
		} catch (final CustomerException e) {
			throw new CustomerException(e.getMessage());
		} catch (final Exception e) {
			log.error("error - {}", e);
			throw new RuntimeErrorException(new Error(e), "Ocorreu um erro ao processar a solicitação!");
		}
	}

	@Override
	public List<CustomerDTO> findAllCustomers() throws CustomerException {
		try {
			log.info("===== Buscando registros na Base de dominio ... =====");
			final List<Customer> clientsFound = repository.findAll();
			if (!clientsFound.isEmpty() && clientsFound != null)
				return Lists.transform(clientsFound, client -> new CustomerDTO(client));
		} catch (final Exception e) {
			throw new CustomerException("Ocorreu um erro ao tentar buscar os dados!", e);
		}
		return new ArrayList<>();
	}

	@Override
	public void updateCustomer(UpdateCustomerCommand command) throws CustomerException {
		/**
		 * Here, for example, you can apply business logic
		 */
		try {
			log.info("===== Atualizando na Base de Dominio ... =====");
			final Long customerId = this.getCustomerId(command.getUuid());
			log.info(">> Customer ID {}", customerId);
			final Customer clientSaved = repository.save(new Customer(customerId, command));
			if (clientSaved.hasError()) {
				throw new CustomerException("Erro ao tentar salvar usuário!");
			}
			log.info("===== [DBWrite] {} atualizado com sucesso !!! =====", clientSaved);
		} catch (final CustomerException e) {
			throw new CustomerException(e.getMessage());
		} catch (final Exception e) {
			log.error("error - {}", e);
			throw new RuntimeErrorException(new Error(e), "Ocorreu um erro ao processar a solicitação!");
		}
	}

	@Override
	public Customer findCustomerByUUID(String uuid) throws CustomerException {

		try {
			log.info("===== Buscando registro na Base de dominio ... =====");
			final Customer customerFound = repository.findByUuid(uuid);
			if (customerFound == null) {
				throw new CustomerException("Nenhum registro encontrado!");
			}
			return customerFound;
		} catch (final CustomerException e) {
			log.info(">> {}", e.getMessage());
			throw new CustomerException(e.getMessage());
		} catch (final Exception e) {
			throw new RuntimeErrorException(new Error(e), "Ocorreu um erro ao processar a solicitação!");
		}
	}

	@Override
	public void deleteCustomerByUUID(String uuid) throws CustomerException {

		try {
			log.info("===== Removendo na Base de Dominio ... =====");
			final Customer customerFound = this.findCustomerByUUID(uuid);
			if (!customerFound.hasError()) {
				repository.delete(customerFound);
			}
			log.info("===== [DBWrite] registro removido com sucesso !!! =====");
		} catch (final CustomerException e) {
			throw new CustomerException(e.getMessage());
		} catch (final Exception e) {
			log.error("error - {}", e);
			throw new RuntimeErrorException(new Error(e), "Ocorreu um erro ao processar a solicitação!");
		}
	}

	private Long getCustomerId(String uuid) throws CustomerException {
		return this.findCustomerByUUID(uuid).getId();
	}

}
