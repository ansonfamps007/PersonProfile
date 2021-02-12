package com.person.profile.bean;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	private int id;
	
	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	private String age;

	private String favouriteColour;
}
