package com.laura.carpaciu.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dao.interfaces.UserRepository;
import com.laura.carpaciu.entity.user.User;
import com.laura.carpaciu.security.securityuser.SecurityUser;

import lombok.AllArgsConstructor;

@Service
public class UserSecurityService implements UserDetailsService {

	private final UserRepository userDao;
	
	@Autowired
	public UserSecurityService(UserRepository userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		if (usernameOrEmail.contains("@")) {

			User user = userDao.findByEmail(usernameOrEmail)
					.orElseThrow(() -> new UsernameNotFoundException("Email not found"));

			return new SecurityUser(user);
		}

		User user = userDao.findByUsername(usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

		return new SecurityUser(user);

	}
}