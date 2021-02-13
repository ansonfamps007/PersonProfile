package com.person.profile.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author anson
 *
 */

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -591187812553708400L;
	
	private final String jwtToken;
}
