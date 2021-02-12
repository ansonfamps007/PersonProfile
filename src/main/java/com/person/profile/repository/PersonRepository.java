package com.person.profile.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.person.profile.model.PersonEntity;


/**
 * @author anson
 *
 */
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

	List<PersonEntity> findAll();
	
	boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
