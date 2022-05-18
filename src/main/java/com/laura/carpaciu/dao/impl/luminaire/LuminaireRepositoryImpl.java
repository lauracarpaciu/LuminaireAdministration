package com.laura.carpaciu.dao.impl.luminaire;

import java.util.Optional;

import com.laura.carpaciu.dao.interfaces.LuminaireRepository;
import com.laura.carpaciu.entity.luminaire.Luminaire;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class LuminaireRepositoryImpl implements LuminaireRepository {

	@PersistenceContext
	private final EntityManager entityManager;

	public LuminaireRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void create(Luminaire luminaire) {
		entityManager.persist(luminaire);
	}

	@Override
	public Optional<Luminaire> findBySerialNumber(String serialNumber) {

		String jpql = "SELECT v FROM Luminaire v WHERE v.serialNumber =: serialNumber";

		return entityManager.createQuery(jpql, Luminaire.class).setParameter("serialNumber", serialNumber)
				.getResultStream().findFirst();
	}

}
