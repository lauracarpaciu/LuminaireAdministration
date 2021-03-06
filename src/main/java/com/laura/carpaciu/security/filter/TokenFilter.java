package com.laura.carpaciu.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.laura.carpaciu.security.authentication.TokenAuthentication;

import lombok.AllArgsConstructor;

public class TokenFilter extends OncePerRequestFilter {

	private final AuthenticationManager authenticationManager;

	public TokenFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String token = request.getParameter("token");

		Authentication authentication = new TokenAuthentication(email, token);

		authentication = authenticationManager.authenticate(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/activate");
	}

}
