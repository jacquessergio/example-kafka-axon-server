package com.example.demo.infrastructure.exceptions;

import lombok.Getter;

@Getter
public class CustomerException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public CustomerException(Throwable th) {
		super(th);
	}

	public CustomerException(String msg) {
		super(msg);
		this.message = msg;
	}

	public CustomerException(String msg, Throwable th) {
		super(msg, th);
		this.message = msg;
	}
}
