package com.laura.carpaciu.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.clients.Person;

public interface PersonService extends CrudRepository<Person, Integer> {

	@Transactional
	void createPerson(Person person);

	@Transactional
	Person findPersonByCnp(String cnp);

}
