package com.laura.carpaciu.security.securityuser;

import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.laura.carpaciu.entity.user.User;

public class SecurityUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final User user;

	public SecurityUser(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> userAuthorities = user.getAuthorities().stream()
				.map(auth -> new SimpleGrantedAuthority("ROLE_" + auth)).collect(Collectors.toList());

		return userAuthorities;
	}
}