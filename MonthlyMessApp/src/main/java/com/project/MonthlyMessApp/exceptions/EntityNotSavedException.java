package com.project.MonthlyMessApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EntityNotSavedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1633597136323825195L;
	String message;

	public EntityNotSavedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
}
