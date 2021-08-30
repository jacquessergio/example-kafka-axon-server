package com.example.demo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.Client;

public interface ClientWriteRepository extends JpaRepository<Client, Long> {

}
