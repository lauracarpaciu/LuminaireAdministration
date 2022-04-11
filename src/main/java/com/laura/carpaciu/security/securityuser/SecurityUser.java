package com.laura.carpaciu.security.securityuser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.laura.carpaciu.entity.user.User;

public class SecurityUser implements UserDetails {

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
        List<GrantedAuthority> authorities =
                new ArrayList<GrantedAuthority>();
        authorities.addAll((Collection<? extends GrantedAuthority>).getUsername();

        return authorities;
        https://stackoverflow.com/questions/32658431/spring-security-role-cannot-be-converted-to-granted-authority
	}
}