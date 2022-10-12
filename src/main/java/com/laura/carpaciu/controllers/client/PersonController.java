package com.laura.carpaciu.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import com.laura.carpaciu.entity.clients.Person;
import com.laura.carpaciu.services.PersonService;
@RestController
public class PersonController {

	
	private final  PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		super();
		this.personService = personService;
	}

	@RequestMapping(value = "/persons", method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@RequestBody Person person) throws Exception {
        return Optional.ofNullable(personService.createPerson(person))
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Not found"));
    }

	@RequestMapping(value = "/persons/{cnp}", method = RequestMethod.GET)
	public ResponseEntity<?> findPersonByCnp(@PathVariable String cnp) throws Exception {
		return Optional.ofNullable(personService.findPersonByCnp(cnp))
				.map(a -> new ResponseEntity<Person>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

}
