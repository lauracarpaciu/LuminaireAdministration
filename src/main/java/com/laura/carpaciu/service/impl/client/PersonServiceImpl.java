package com.laura.carpaciu.service.impl.client;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dao.interfaces.PersonRepository;
import com.laura.carpaciu.entity.clients.Person;
import com.laura.carpaciu.errors.client.PersonAlreadyExistsException;
import com.laura.carpaciu.services.PersonService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;

	@Override
	@Transactional
	public void createPerson(Person person) {

		Optional<Person> optPerson = personRepository.findPersonByCnp(person.getCnp());

		if (optPerson.empty() != null) {
			personRepository.createPerson(person);
			return;
		}

		throw new PersonAlreadyExistsException("Person already exists");
	}

	public PersonServiceImpl(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}

	@Override
	@Transactional
	public Person findPersonByCnp(String cnp) {

		return personRepository.findPersonByCnp(cnp).orElseThrow();

	}

}