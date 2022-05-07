package com.laura.carpaciu.dao.interfaces;

//Spring Data JPA also lets you define other query methods by declaring their method signature.
//You need not write an implementation of the repository interface. Spring Data JPA creates an implementation when you run the application.

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.luminaire.Luminaire;
@Repository
public interface LuminaireRepository extends CrudRepository<Luminaire, Long> {
	Optional<Luminaire> findBySerialNumber(String serialNumber);

	void create(Luminaire luminaire);

}
