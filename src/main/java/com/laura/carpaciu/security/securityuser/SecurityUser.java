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
    public Collection<? extends GrantedAuthority> getAuthorities() {


        List<GrantedAuthority> userAuthorities = user.getAuthorities().stream()
                                                                .map( auth -> new SimpleGrantedAuthority("ROLE_"+ auth))
                                                                .collect(Collectors.toList());

        return userAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isNonLoked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}