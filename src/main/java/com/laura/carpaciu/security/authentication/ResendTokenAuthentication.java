package com.laura.carpaciu.security.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class ResendTokenAuthentication extends UsernamePasswordAuthenticationToken {
	public ResendTokenAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public ResendTokenAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
