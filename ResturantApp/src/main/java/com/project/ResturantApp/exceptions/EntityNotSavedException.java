package com.project.ResturantApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EntityNotSavedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public EntityNotSavedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
}
