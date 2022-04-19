package com.laura.carpaciu.errors.logout;

public class AccountAlreadyActiveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountAlreadyActiveException() {

	}

	public AccountAlreadyActiveException(String message) {
		super(message);
		
	}

}
