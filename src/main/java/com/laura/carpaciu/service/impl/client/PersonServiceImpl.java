package com.laura.carpaciu.service.impl.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dao.interfaces.PersonRepository;
import com.laura.carpaciu.entity.clients.Person;
import com.laura.carpaciu.errors.client.PersonAlreadyExistsException;
import com.laura.carpaciu.errors.user.PersonNotFoundException;
import com.laura.carpaciu.services.PersonService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private final PersonRepository personRepository;

	@Override
	@Transactional
	public void createPerson(Person person) {

		Optional<Person> optPerson = personRepository.findByCnp(person.getCnp());

		if (optPerson.isPresent()) {
			personRepository.create(person);
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

		return personRepository.findByCnp(cnp).orElseThrow(() -> new PersonNotFoundException("Person not found!"));

	}

}