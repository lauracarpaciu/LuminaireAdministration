package com.laura.carpaciu.controllers.luminaire;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laura.carpaciu.entity.luminaire.Luminaire;
import com.laura.carpaciu.services.LuminaireService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/luminaires")
public class LuminareController {

	public LuminareController(LuminaireService luminaireService) {
		super();
		this.luminaireService = luminaireService;
	}

	private final LuminaireService luminaireService;

	@GetMapping("/luminaire")
	public String getLuminairePage(Model model) {

		model.addAttribute("luminaire", new Luminaire());

		return "auto/luminaire-page";
	}

	@PostMapping("/create-luminaire")
	public String createLuminiare(@Valid @ModelAttribute("luminaire") Luminaire luminaire,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "auto/luminaire-page";
		}

		luminaireService.createLuminaire(luminaire);
		return "redirect:/luminaires/luminaire";

	}

}
