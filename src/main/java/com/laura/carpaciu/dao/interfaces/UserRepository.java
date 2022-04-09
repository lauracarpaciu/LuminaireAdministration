package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.laura.carpaciu.entity.user.User;

public interface UserRepository extends CrudRepository<User, Long> {

	int activateUserAccount(User user);

	void createUser(User user);

	Optional<User> findByUsername(String username);

	Optional<User> findWithToken(String email);

	Optional<User> findByEmail(String email);

}
