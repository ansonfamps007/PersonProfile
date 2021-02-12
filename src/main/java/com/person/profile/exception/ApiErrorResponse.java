package com.person.profile.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author anson
 *
 */
@Getter
@Setter
@Builder
@ToString
public class ApiErrorResponse {

	private String errorCode;
	private String errorMsg;
	private int status;

}
