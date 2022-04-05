package com.laura.carpaciu.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class SecurityLogoutHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		if (authentication != null) {

			SecurityContextLogoutHandler context = new SecurityContextLogoutHandler();
			context.logout(request, response, authentication);

		}

	}

}
