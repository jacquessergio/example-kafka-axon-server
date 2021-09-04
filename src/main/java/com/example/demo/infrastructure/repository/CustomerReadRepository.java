package com.example.demo.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.infrastructure.query.model.Customer;

public interface CustomerReadRepository extends MongoRepository<Customer, String> {

	public Customer findByUuid(UUID uuid);
}
