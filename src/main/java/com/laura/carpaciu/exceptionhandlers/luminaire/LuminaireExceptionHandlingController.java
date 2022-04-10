package com.laura.carpaciu.exceptionhandlers.luminaire;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.laura.carpaciu.errors.luminaire.LuminaireAlreadyExistsException;
import com.laura.carpaciu.errors.luminaire.LuminaireNotFoundException;

@ControllerAdvice
public class LuminaireExceptionHandlingController {

	@ExceptionHandler(LuminaireAlreadyExistsException.class)
	public String luminaireAlreadyExists(LuminaireAlreadyExistsException e) {
		e.printStackTrace();
		return "redirect:/luminaires/luminaire?exists";

	}

	@ExceptionHandler(LuminaireNotFoundException.class)
	public String luminaireNotFound(LuminaireNotFoundException e) {
		e.printStackTrace();
		return "redirect:/serviceOrder/serviceOrder?luminaireNotFound";
	}

}