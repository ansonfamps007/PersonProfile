package com.person.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "person")
@Data
public class PersonEntity {

	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@Column
	@NotBlank
	private String firstName;
	
	@Column
	@NotBlank
	private String lastName;
	
	@Column
	private String age;
	
	@Column
	private String favouriteColour;
	
}
