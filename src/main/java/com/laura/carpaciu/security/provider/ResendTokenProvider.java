package com.laura.carpaciu.security.provider;

import java.util.Optional;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.laura.carpaciu.entity.user.User;
import com.laura.carpaciu.errors.logout.AccountAlreadyActiveException;
import com.laura.carpaciu.errors.logout.EmailIsNotRegisteredException;
import com.laura.carpaciu.security.authentication.ResendTokenAuthentication;
import com.laura.carpaciu.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ResendTokenProvider implements AuthenticationProvider {

	private final UserService userService;

	public ResendTokenProvider(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = authentication.getName();

		User user = userService.findUserWithToken(email).orElseThrow();

		return Optional.of(user).filter(u -> u.getActivationToken().getActivatedAt() == null)
				.map(this::resendTokenAuthentication)
				.orElseThrow(() -> new AccountAlreadyActiveException("Account is already active"));

	}

	private ResendTokenAuthentication resendTokenAuthentication(User user) {
		userService.updateUserToken(user);

		return new ResendTokenAuthentication(user.getEmail(), null, null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return ResendTokenAuthentication.class.equals(authentication);
	}
}