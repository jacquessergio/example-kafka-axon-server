package com.example.demo.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.infrastructure.query.model.Client;


public interface ClientReadRepository extends MongoRepository<Client, String> {

}
