package com.example.demo.infrastructure.exceptions;

public class ClientException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClientException(String msg) {
		super(msg);
	}
	
	public ClientException(String msg, Throwable tx) {
		super(msg, tx);
	}
}
