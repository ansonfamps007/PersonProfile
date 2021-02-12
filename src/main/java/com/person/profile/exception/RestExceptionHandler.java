
package com.person.profile.exception;

import java.util.HashMap;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 * The type Rest exception handler.
 */
@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * 
	 * Validation exception handler api error response.
	 * 
	 * @param ex the ex
	 * @return
	 * @return the api error response
	 */
	@ExceptionHandler(value = { ValidationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse validationExceptionHandler(final ValidationException ex) {
		return ApiErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.name()).errorMsg(ex.getMessage())
				.status(HttpStatus.BAD_REQUEST.value()).build();
	}

	/**
	 * 
	 * Bad request exception handler api error response.
	 * 
	 * @param ex the ex
	 * @return
	 * @return the api error response
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationExceptions(final MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	
	@ExceptionHandler(value = { AuthenticationException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiErrorResponse authenticationExceptionHandler(final AuthenticationException ex) {
		return ApiErrorResponse.builder().errorCode(HttpStatus.UNAUTHORIZED.name()).errorMsg(ex.getMessage())
				.status(HttpStatus.UNAUTHORIZED.value()).build();
	}
	
	
	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErrorResponse otherExceptionHandler(final Exception ex) {
		return ApiErrorResponse.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.name()).errorMsg(ex.getMessage())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
	}

}
