package com.laura.carpaciu.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.laura.carpaciu.entity.user.ActivationToken;

@Controller
public class ActivateAccountController {

	@GetMapping("/token")
	public String showExpiredTokenPage(Model model) {

		model.addAttribute("token", new ActivationToken());

		return "activation/resend-token";
	}

	@PostMapping("/resendToken")
	public String activateExpiredToken(@ModelAttribute("token") ActivationToken activationToken) {

		return "redirect:/login";
	}

	@GetMapping("/account")
	public String accountActivePage() {

		return "activation/account-info";

	}
}