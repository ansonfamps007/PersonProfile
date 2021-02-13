package com.person.profile.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.person.profile.bean.Person;
import com.person.profile.exception.ValidationException;
import com.person.profile.service.PersonService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author anson
 *
 */

@RestController
@Slf4j
public class PersonController {

	@Autowired
	private PersonService personService;

	/**
	 * @param person
	 * @return
	 */
	@PostMapping(value = "/createPerson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createPerson(@Valid @RequestBody Person person) {
		log.info("PersonController: Create Person");
		if (!personService.existsByFirstNameAndLastName(person.getFirstName(), person.getLastName())) {
			personService.save(person);
			return "Created Successfully";
		} else {
			throw new ValidationException("Person already exist !");
		}
	}

	/**
	 * @return
	 */
	@GetMapping(value = "/getAllPerson", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> getAllPerson() {

		List<Person> personsList = personService.getAllPerson();

		if (!CollectionUtils.isEmpty(personsList)) {
			return personsList;
		} else {
			throw new ValidationException("No data found !");
		}

	}

	/**
	 * @return
	 */
	@GetMapping(value = "/getPersonByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person getPersonByName(@RequestParam String firstName, @RequestParam String lastName) {

		Person person = personService.getPersonByName(firstName, lastName);

		if (null != person) {
			return person;
		} else {
			throw new ValidationException("No data found !");
		}

	}

	/**
	 * @param person
	 * @param id
	 * @return
	 */
	@PatchMapping(value = "/updatePerson/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updatePerson(@Valid @RequestBody Person person, @PathVariable int id) {
		int flag = personService.update(person, id);
		if (flag > 0) {
			return "Updated Successfully";
		} else {
			throw new ValidationException("Update Failed !");
		}
	}

	/**
	 * @param id
	 */
	@DeleteMapping(value = "/deletePerson/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletePerson(@PathVariable int id) {
		personService.delete(id);
	}

}
