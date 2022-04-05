package com.laura.carpaciu.security.authentication;

import java.util.Collection;

public class EmailAuthentication extends UserNamePasswordAuthentication {
	public EmailAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public EmailAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
