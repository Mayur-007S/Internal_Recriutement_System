package com.api.customeException;

import java.util.Set;


public class ObjectNotValidateException extends Exception {

	private Set<String> errorMessage;
	
	public ObjectNotValidateException(Set<String> errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Set<String> getErrorMessage() {
		return errorMessage;
	}

}
