package com.laura.carpaciu.services;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.luminaire.Luminaire;

public interface LuminaireService {
	@Transactional
	void createLuminaire(Luminaire luminaire);

	@Transactional
	Luminaire findLuminaireBySerialNumber(String serialNumber);

}