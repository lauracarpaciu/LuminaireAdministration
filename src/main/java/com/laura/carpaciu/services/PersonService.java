package com.laura.carpaciu.services;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.clients.Person;

public interface PersonService {

	@Transactional
	void createPerson(Person person);

	@Transactional
	Person findPersonByCnp(String cnp);

}
