package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.clients.Person;
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	Optional<Person> findByCnp(String cnp);

	void create(Person person);

}
