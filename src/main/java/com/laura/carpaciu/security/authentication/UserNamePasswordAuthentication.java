package com.laura.carpaciu.security.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserNamePasswordAuthentication extends UsernamePasswordAuthenticationToken {
	public UserNamePasswordAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public UserNamePasswordAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}