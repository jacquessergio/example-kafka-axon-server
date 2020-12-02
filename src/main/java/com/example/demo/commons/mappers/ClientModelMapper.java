package com.example.demo.commons.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientModelMapper<S, T> {

	@Autowired
	private ModelMapper modelMapper;

	public T convertDtoToModel(final S source, final Class<T> target) {
		return modelMapper.map(source, target);
	}

	public S convertModelToDto(final T source, final Class<S> target) {
		return modelMapper.map(source, target);
	}
}
