package com.person.profile.service;

import java.util.List;

import com.person.profile.bean.Person;

public interface PersonService {

	int save(Person person);
	
	List<Person> getAllPerson();

	int update(Person person, int id);

	void delete(int id);
	
	boolean existsByFirstNameAndLastName(String firstName, String lastName);

}
