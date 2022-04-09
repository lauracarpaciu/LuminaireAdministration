package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

	Optional<Person> findByCnp(String cnp);

	void create(Person person);

}
