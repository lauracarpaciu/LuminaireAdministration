package com.laura.carpaciu.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.laura.carpaciu.errors.logout.AccountIsNotActiveException;
import com.laura.carpaciu.security.authentication.UserNamePasswordAuthentication;
import com.laura.carpaciu.security.service.UserSecurityService;

import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class UserNamePasswordProvider implements AuthenticationProvider {

	private final UserSecurityService userSecurityService;
	private final PasswordEncoder passwordEncoder;

	public UserNamePasswordProvider(UserSecurityService userSecurityService, PasswordEncoder passwordEncoder) {
		super();
		this.userSecurityService = userSecurityService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetails user = userSecurityService.loadUserByUsername(username);

		if (passwordEncoder.matches(password, user.getPassword())) {

			if (user.isEnabled() && user.isAccountNonLocked()) {
				return new UserNamePasswordAuthentication(username, password, user.getAuthorities());
			}

			throw new AccountIsNotActiveException("Account is not activated");
		}

		throw new BadCredentialsException("Bad Credential Exception");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return UserNamePasswordAuthentication.class.equals(aClass);
	}
}