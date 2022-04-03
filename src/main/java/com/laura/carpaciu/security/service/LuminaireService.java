package com.laura.carpaciu.security.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.luminaire.Luminaire;

public interface LuminaireService {
	void createLuminaire(Luminaire luminaire);

	Optional<Luminaire> findLuminaireBySerialNumber(String serialNumber);

}
