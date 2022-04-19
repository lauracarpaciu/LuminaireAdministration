package com.laura.carpaciu.errors.user;

public class UserAlreadyExists extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExists() {
		
	}

	public UserAlreadyExists(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
