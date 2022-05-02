package com.laura.carpaciu.security.provider;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.laura.carpaciu.entity.user.ActivationToken;
import com.laura.carpaciu.entity.user.User;
import com.laura.carpaciu.errors.invoice.TokenHasExpiredException;
import com.laura.carpaciu.errors.logout.AccountAlreadyActiveException;
import com.laura.carpaciu.errors.luminaire.InvalidTokenException;
import com.laura.carpaciu.security.authentication.TokenAuthentication;
import com.laura.carpaciu.services.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TokenProvider implements AuthenticationProvider {


	private final UserService userService;
	@Autowired
	public TokenProvider(UserService userService) {
		super();
		this.userService = userService;
	}


	// method 1
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = authentication.getName();
		String token = authentication.getCredentials().toString();
		User user = userService.findUserWithToken(email).orElseThrow(null);

		ActivationToken userToken = user.getActivationToken();

		Optional.of(userToken).filter(t -> t.getActivatedAt() == null)
				.orElseThrow(() -> new AccountAlreadyActiveException("Account was already activated"));

		Optional.of(userToken).filter(t -> !t.getExpiredAt().isBefore(LocalDateTime.now()))
				.orElseThrow(() -> new TokenHasExpiredException("Token has expired"));

		Optional.of(userToken).filter(t -> t.getToken().equals(token))
				.orElseThrow(() -> new InvalidTokenException("Invalid Token"));

		return Optional.of(user).map(this::authenticate)
				.orElseThrow(() -> new BadCredentialsException("Bad credentials"));
	}

	// method 2
	private Authentication authenticate(User user) {
//		userService.activateUserAccount(user);
		return new TokenAuthentication(user.getActivationToken().getToken(), null, null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return TokenAuthentication.class.equals(authentication);
	}

}