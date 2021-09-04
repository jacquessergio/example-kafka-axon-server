package com.example.demo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.Customer;

public interface CustomerWriteRepository extends JpaRepository<Customer, Long> {

	public Customer findByUuid(String uuid);
}
