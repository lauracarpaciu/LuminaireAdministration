package com.laura.carpaciu.controllers.user;

import com.laura.carpaciu.entity.user.User
import com.laura.carpaciu.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(User user) throws Exception {
		return Optional.ofNullable(userService.createUser(user))
				.map(a -> new ResponseEntity<Future<User>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> updateUserToken(User user) throws Exception {
		return Optional.ofNullable(userService.updateUserToken(user))
				.map(a -> new ResponseEntity<List<Employee>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<?> findUserWithToken(String email) throws Exception {
		return Optional.ofNullable(userService.findUserWithToken(email))
				.map(a -> new ResponseEntity<Optional<User>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> activateUserAccount(User user) throws Exception {
		return Optional.ofNullable(userService.activateUserAccount(user))
				.map(a -> new ResponseEntity<Optional<User>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> findUseByUsername(String username) throws Exception {
		return Optional.ofNullable(userService.findUseByUsername(username))
				.map(a -> new ResponseEntity<Optional<User>>(HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

}
