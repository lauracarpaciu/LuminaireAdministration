package com.laura.carpaciu.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.laura.carpaciu.cache.MiniCache;

public class CacheLogoutHandler implements LogoutHandler {

	@Autowired
	private MiniCache miniCache;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		miniCache.getOrder().remove(username);
		miniCache.getWorks().remove(username);
		miniCache.getOrderWorks().remove(username);
		miniCache.getParts().remove(username);
		miniCache.getLuminaire().remove(username);
		miniCache.getPerson().remove(username);
		miniCache.getCompany().remove(username);
	}
}
