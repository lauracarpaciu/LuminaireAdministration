package com.laura.carpaciu.controllers.user;

import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.laura.carpaciu.entity.user.Authorities;
import com.laura.carpaciu.entity.user.User;
import com.laura.carpaciu.errors.user.UserAlreadyExists;
import com.laura.carpaciu.services.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {
	@Autowired
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "user/login-user";
	}

	@PostMapping("/login-processing")
	public String loginProcessing() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

			return "redirect:/login";
		}

		return "redirect:/app/main";
	}

	@GetMapping("/createUserPage")
	public String showCreateUserPage(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("authority", Authorities.values());
		return "user/create-user";
	}

	@PostMapping("/create-user")
	public String createUser(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("authority", Authorities.values());
			return "user/create-user";
		}

		try {
			userService.createUser(user).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new UserAlreadyExists("User already exists");
		}

		return "redirect:/login";
	}

	@GetMapping("/activate")
	public String activateAccount() {
		return "activation/confirmAccount-activation";
	}

}