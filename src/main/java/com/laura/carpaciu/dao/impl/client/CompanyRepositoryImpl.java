package com.laura.carpaciu.dao.impl.client;

import com.laura.carpaciu.dao.interfaces.CompanyRepository;
import com.laura.carpaciu.entity.clients.Company;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class CompanyRepositoryImpl implements CompanyRepository {

	@PersistenceContext
	private final EntityManager entityManager;

	public CompanyRepositoryImpl(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	@Override
	public void create(Company company) {
		entityManager.persist(company);

	}

	@Override
	public Optional<Company> findByCui(String cui) {

		String jpql = "SELECT c FROM Company c WHERE c.cui =: cui";

		return entityManager.createQuery(jpql, Company.class).setParameter("cui", cui).getResultStream().findFirst();
	}

}
