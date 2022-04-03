package com.laura.carpaciu.dao.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.clients.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{

	Optional<Person> findPersonByCnp(String cnp);
//	
//	void createPerson(Person person);
//
//	Optional<Person> findPersonByCnp(String cnp);
//
//	List<Person> findPersonByFirstNameOrLastName(String firstOrLastName);

	void createPerson(Person person);

}
