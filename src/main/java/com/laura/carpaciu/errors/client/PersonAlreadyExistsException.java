package com.laura.carpaciu.errors.client;

public class PersonAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonAlreadyExistsException() {
	}

	public PersonAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
