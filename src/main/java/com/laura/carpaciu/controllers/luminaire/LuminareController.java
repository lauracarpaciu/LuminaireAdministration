package com.laura.carpaciu.controllers.luminaire;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	private final LuminaireService luminaireService;
	
	@Autowired
	public LuminareController(LuminaireService luminaireService) {
		super();
		this.luminaireService = luminaireService;
	}
	

	@GetMapping("/luminaire")
	public String getLuminairePage(Model model) {

		model.addAttribute("luminaire", new Luminaire());

		return "luminaire/luminaire-page";
	}

	@PostMapping("/create-luminaire")
	public String createLuminiare(@Valid @ModelAttribute("luminaire") Luminaire luminaire,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "luminaire/luminaire-page";
		}

		luminaireService.createLuminaire(luminaire);
		return "redirect:/luminaires/luminaire";

	}

}
