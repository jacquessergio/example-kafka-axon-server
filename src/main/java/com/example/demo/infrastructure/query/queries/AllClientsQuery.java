package com.example.demo.infrastructure.query.queries;

import java.util.List;

import com.example.demo.infrastructure.dto.ClientDTO;
import com.example.demo.infrastructure.query.model.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllClientsQuery {

	List<Client> clients;
}
