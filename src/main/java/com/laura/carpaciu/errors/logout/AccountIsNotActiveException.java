package com.laura.carpaciu.errors.logout;

public class AccountIsNotActiveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountIsNotActiveException() {

	}

	public AccountIsNotActiveException(String message) {
		super(message);
		
	}

}
