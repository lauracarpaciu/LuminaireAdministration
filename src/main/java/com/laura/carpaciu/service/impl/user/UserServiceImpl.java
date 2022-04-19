package com.laura.carpaciu.service.impl.user;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dao.interfaces.TokenRepository;
import com.laura.carpaciu.dao.interfaces.UserRepository;
import com.laura.carpaciu.email.sender.EmailSender;
import com.laura.carpaciu.entity.user.ActivationToken;
import com.laura.carpaciu.entity.user.User;
import com.laura.carpaciu.errors.user.UserAlreadyExists;
import com.laura.carpaciu.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final EmailSender emailSender;
	private final TokenRepository tokenRepository;



	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailSender emailSender,
			TokenRepository tokenRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.emailSender = emailSender;
		this.tokenRepository = tokenRepository;
	}

	@Override
	@Async
	@Transactional
	public Future<User> createUser(User user) {

		Optional<User> user1 = userRepository.findByUsername(user.getUsername());

		if (user1.isPresent()) {

			user1 = userRepository.findByEmail(user.getEmail());

			if (user1.isPresent()) {

				String encodedPassword = passwordEncoder.encode(user.getPassword());
				user.setPassword(encodedPassword);
				String token = UUID.randomUUID().toString();

				ActivationToken activationToken = createActivationToken(token, user);
				user.setActivationToken(activationToken);

				userRepository.createUser(user);
				emailSender.sendEmail(user);

				return new AsyncResult<>(user);
			}
		}

		throw new UserAlreadyExists("User already exists");
	}

	private ActivationToken createActivationToken(String token, User user) {

		ActivationToken activationToken = new ActivationToken();
		LocalDateTime createdAt = LocalDateTime.now();
		LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(30);

		activationToken.setToken(token);
		activationToken.setCreatedAt(createdAt);
		activationToken.setExpiredAt(expiredAt);
		activationToken.setUser(user);

		return activationToken;
	}

	@Override
	@Async
	@Transactional
	public void updateUserToken(User user) {

		String token = UUID.randomUUID().toString();
		user.getActivationToken().setToken(token);

		tokenRepository.updateToken(user.getId(), token);
		emailSender.sendEmail(user);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findUserWithToken(String email) {
		return userRepository.findWithToken(email);
	}

	@Override
	@Transactional
	public int activateUserAccount(User user) {
		return userRepository.activateUserAccount(user);

	}

	@Override
	@Transactional
	public User findUseByUsername(String username) {

		return userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not Found in security context"));
	}

	

}