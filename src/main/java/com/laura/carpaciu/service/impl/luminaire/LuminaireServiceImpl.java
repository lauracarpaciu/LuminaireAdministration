package com.laura.carpaciu.service.impl.luminaire;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laura.carpaciu.dao.interfaces.LuminaireRepository;
import com.laura.carpaciu.entity.luminaire.Luminaire;
import com.laura.carpaciu.errors.luminaire.LuminaireAlreadyExistsException;
import com.laura.carpaciu.errors.user.PersonNotFoundException;
import com.laura.carpaciu.services.LuminaireService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LuminaireServiceImpl implements LuminaireService {

	private final LuminaireRepository luminaireRepository;
	
	@Autowired
	public LuminaireServiceImpl(LuminaireRepository luminaireRepository) {
		super();
		this.luminaireRepository = luminaireRepository;
	}
	

	@Override
	public void createLuminaire(Luminaire luminaire) {
		Optional<Luminaire> optLuminaire = luminaireRepository.findBySerialNumber(luminaire.getSerialNumber());

		if (optLuminaire.isPresent()) {
			luminaireRepository.create(luminaire);
			return;
		}

		throw new LuminaireAlreadyExistsException("Luminaire already exists");

	}

	@Override
	public Luminaire findLuminaireBySerialNumber(String serialNumber) {
		return luminaireRepository.findBySerialNumber(serialNumber).orElseThrow(() -> new PersonNotFoundException("Person not found!"));
	}

}
