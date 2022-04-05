package com.laura.carpaciu.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.laura.carpaciu.security.authentication.EmailAuthentication;
import com.laura.carpaciu.security.authentication.UserNamePasswordAuthentication;

public class UsernameAndPasswordFilter extends OncePerRequestFilter {

	private final AuthenticationManager authenticationManager;

	@Autowired
	public UsernameAndPasswordFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String usernameOrEmail = request.getParameter("username");
		String password = request.getParameter("password");

		Authentication auth = null;

		if (usernameOrEmail.contains("@")) {

			auth = new EmailAuthentication(usernameOrEmail, password);

		} else {

			auth = new UserNamePasswordAuthentication(usernameOrEmail, password);

		}

		auth = authenticationManager.authenticate(auth);
		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/login-processing");
	}

}
