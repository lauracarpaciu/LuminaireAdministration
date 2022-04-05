package com.laura.carpaciu.security.authentication;

import java.util.Collection;

public class TokenAuthentication extends UserNamePasswordAuthentication {
	public TokenAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public TokenAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
