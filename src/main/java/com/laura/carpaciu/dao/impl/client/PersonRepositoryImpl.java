package com.laura.carpaciu.dao.impl.client;

import com.laura.carpaciu.dao.interfaces.PersonRepository;
import com.laura.carpaciu.entity.clients.Person;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class PersonRepositoryImpl implements PersonRepository {

	private final EntityManager entityManager;

	public PersonRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void create(Person person) {
		entityManager.persist(person);
	}

	@Override
	public Optional<Person> findByCnp(String cnp) {

		String jpql = "SELECT p FROM Person p WHERE p.cnp =: cnp";

		return entityManager.createQuery(jpql, Person.class).setParameter("cnp", cnp).getResultStream().findFirst();

	}

}
