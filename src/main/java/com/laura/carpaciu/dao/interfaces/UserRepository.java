package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.user.User;

public interface UserRepository extends CrudRepository<User, Integer>{
//	void createUser(User user);
//
//	Optional<User> findUserByUsername(String username);
//
//	Optional<User> findUserByEmail(String email);
//
//	int activateUserAccount(User user);
//
//	Optional<User> findUserWithToken(String email);

}
