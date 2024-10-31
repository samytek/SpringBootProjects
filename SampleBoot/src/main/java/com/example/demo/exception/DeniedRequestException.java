package com.example.demo.exception;

import org.springframework.validation.Errors;

@SuppressWarnings("serial")
public class DeniedRequestException extends RuntimeException {
	private Errors errors;
	
	public DeniedRequestException(String message, Errors errors) {
		super(message);
		this.errors = errors;
	}	
}