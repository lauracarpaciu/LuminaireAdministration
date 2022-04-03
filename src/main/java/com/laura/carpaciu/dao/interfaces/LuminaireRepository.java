package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.luminaire.Luminaire;

public interface LuminaireRepository extends CrudRepository<Luminaire, Integer> {

	Optional<Luminaire> findLuminaireBySerialNumber(String serialNumber);
//	void createLuminaire(Luminaire luminaire);
//
//	Optional<Luminaire> findLuminaireBySerialNumber(String serialNumber);

	void createLuminaire(Luminaire luminaire);

}
