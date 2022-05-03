package com.laura.carpaciu.services;

import java.util.Optional;
import java.util.concurrent.Future;

import javax.transaction.Transactional;

import com.laura.carpaciu.entity.user.User;

public interface UserService {
	Future<User> createUser(User user);

	void updateUserToken(User user);

	Optional<User> findUserWithToken(String email);

	Optional<User> activateUserAccount(User user);

	@Transactional
	User findUseByUsername(String username);

}
