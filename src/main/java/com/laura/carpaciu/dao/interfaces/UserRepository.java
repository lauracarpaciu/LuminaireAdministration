package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.user.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
//	int activateUserAccount(User user);

//	void createUser(User user);

	Optional<User> findByUsername(String username);

//	Optional<User> findWithToken(@Param("mail") String email);

	Optional<User> findByEmail(@Param("mail")String email);

}
