package com.project.MonthlyMessApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoResourceFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	String message;
	
	public NoResourceFoundException(String message) {
		super(message);
	}
	
	
}
