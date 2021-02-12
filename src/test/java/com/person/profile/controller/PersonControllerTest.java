package com.person.profile.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.profile.bean.Person;
import com.person.profile.common.Constants;
import com.person.profile.security.JwtTokenUtil;
import com.person.profile.service.PersonService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

	@MockBean
	private PersonService personService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public String getToken() {
		UserDetails userDetails = new User(Constants.USER_NAME, Constants.PWD, new ArrayList<>());
		return jwtTokenUtil.generateToken(userDetails);
	}

	@Test
	public void testGetAllPerson() throws Exception {

		List<Person> personList = new ArrayList<>();
		Person person = new Person();
		person.setFirstName("First Name");
		person.setLastName("Last Name");
		person.setAge("30");
		person.setFavouriteColour("Blue");
		personList.add(person);
		when(personService.getAllPerson()).thenReturn(personList);
		MvcResult result = mockMvc.perform(get("/getAllPerson").header("Authorization", "Bearer " + getToken()))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result);

	}

	@Test
	public void testCreatePerson() throws Exception {

		Person person = new Person();
		person.setFirstName("First Name");
		person.setLastName("Last Name");
		person.setAge("30");
		person.setFavouriteColour("Blue");
		when(personService.save(person)).thenReturn(1);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/createPerson").header("Authorization", "Bearer " + getToken())
				.content(mapper.writeValueAsString(person)).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE))/* .andExpect(status().isOk()).andReturn() */;
	}

	@Test
	public void testUpdatePerson() throws Exception {

		Person person = new Person();
		person.setId(1);
		person.setFirstName("First Name Updated");
		person.setLastName("Last Name Updated");
		person.setAge("30");
		person.setFavouriteColour("Blue");
		when(personService.update(person, 1)).thenReturn(1);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(patch("/updatePerson/1").header("Authorization", "Bearer " + getToken())
				.content(mapper.writeValueAsString(person)).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE))/* .andExpect(status().isOk()).andReturn() */;
	}

	@Test
	public void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/deletePerson/1").header("Authorization", "Bearer " + getToken())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE))/* .andExpect(status().isOk()).andReturn() */;
	}

}
