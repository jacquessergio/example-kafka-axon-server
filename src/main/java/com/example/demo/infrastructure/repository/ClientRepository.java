package com.example.demo.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.model.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

}
