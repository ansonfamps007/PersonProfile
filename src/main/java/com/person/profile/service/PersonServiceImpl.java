package com.person.profile.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.person.profile.bean.Person;
import com.person.profile.exception.ValidationException;
import com.person.profile.model.PersonEntity;
import com.person.profile.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	@Transactional
	public int save(Person person) {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setFirstName(person.getFirstName());
		personEntity.setLastName(person.getLastName());
		personEntity.setAge(person.getAge());
		personEntity.setFavouriteColour(person.getFavouriteColour());
		return personRepository.save(personEntity).getId();
	}

	@Override
	@Transactional
	public int update(Person person, int id) {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setFirstName(person.getFirstName());
		personEntity.setLastName(person.getLastName());
		personEntity.setAge(person.getAge());
		personEntity.setFavouriteColour(person.getFavouriteColour());
		personEntity.setId(id);
		return personRepository.save(personEntity).getId();
	}

	@Override
	public List<Person> getAllPerson() {
		List<Person> personList = new ArrayList<>();
		List<PersonEntity> personEntities = personRepository.findAll();
		if (!CollectionUtils.isEmpty(personEntities)) {
			personEntities.forEach(personEntity -> {
				personList.add(Person.builder().id(personEntity.getId()).firstName(personEntity.getFirstName())
						.lastName(personEntity.getLastName()).age(personEntity.getAge())
						.favouriteColour(personEntity.getFavouriteColour()).build());
			});

		}

		return personList;

	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			personRepository.deleteById(id);
		} catch (Exception e) {
			throw new ValidationException("Deletion Failed ! " + e.getMessage());
		}
	}

	@Override
	public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
		return personRepository.existsByFirstNameAndLastName(firstName, lastName);
	}

}
