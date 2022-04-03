package com.laura.carpaciu.services;

import java.util.Optional;
import java.util.concurrent.Future;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.user.User;

public interface UserService extends CrudRepository<User, Integer> {
	Future<User> createUser(User user);

	void updateUserToken(User user);

	Optional<User> findUserWithToken(String email);

	int activateUserAccount(User user);

	@Transactional
	User findUseByUsername(String username);

}
