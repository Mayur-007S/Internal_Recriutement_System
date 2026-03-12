package com.api.validation;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.api.customeException.ObjectNotValidateException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Component
public class ObjectValidation<T> {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();
	
	public void validate(T objectToValidate) throws ObjectNotValidateException {
		Set<ConstraintViolation<T>> vialation = validator.validate(objectToValidate);
		
		var errorMessage = vialation.stream()
					.map(ConstraintViolation::getMessage)
					.collect(Collectors.toSet());
		throw new ObjectNotValidateException(errorMessage);
	}
}
