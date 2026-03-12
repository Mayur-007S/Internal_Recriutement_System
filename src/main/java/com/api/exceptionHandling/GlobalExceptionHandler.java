package com.api.exceptionHandling;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.api.customeException.ObjectNotValidateException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(exception = ObjectNotValidateException.class)
	public ResponseEntity<Set<String>> handleObjectValidateException(ObjectNotValidateException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
	}

}
