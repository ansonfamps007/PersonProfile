
package com.person.profile.exception;

/**
 * 
 * The type Validation exception.
 */
public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8218426679915146915L;

	/**
	 * 
	 * Instantiates a new Validation exception.
	 * 
	 * @param message the message
	 */
	public ValidationException(final String message) {
		super(message);
	}
}
