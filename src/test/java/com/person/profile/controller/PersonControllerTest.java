package com.person.profile.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.profile.bean.Person;
import com.person.profile.service.PersonService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

	private static final String TEST_USER_ID = "test_user";

	@MockBean
	private PersonService personService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreatePerson() throws Exception {

		Person person = new Person();
		person.setFirstName("FirstName");
		person.setLastName("LastName");
		person.setAge("30");
		person.setFavouriteColour("Blue");
		when(personService.save(person)).thenReturn(1);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/createPerson").with(user(TEST_USER_ID)).with(csrf()).content(mapper.writeValueAsString(person))
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	/*@Test
	public void getAllItemCategories() throws Exception {

		List<ItemCategory> itemCategories = new ArrayList<>();
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setItemCategory("testItem");
		itemCategory.setMaxPrice(100);
		itemCategory.setMinPrice(50);
		itemCategories.add(itemCategory);
		when(inventoryService.loadItemCategories()).thenReturn(itemCategories);
		MvcResult result = mockMvc
				.perform(get("/svc/getAllItemCategories").with(user(TEST_USER_ID)).with(csrf()))
				.andExpect(status().isOk()).andReturn();
		ObjectMapper mapper = new ObjectMapper();
		final CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
				ItemCategory.class);
		final List<ItemCategory> mockList = mapper.readValue(result.getResponse().getContentAsString(), typeReference);
		assertEquals(itemCategories.get(0).getItemCategory(), mockList.get(0).getItemCategory());
	}*/

	
}
