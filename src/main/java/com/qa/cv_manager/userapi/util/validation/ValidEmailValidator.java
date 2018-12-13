package com.qa.cv_manager.userapi.util.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.qa.cv_manager.userapi.util.constants.Constants;


public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {

	@Override
	public void initialize(ValidEmail constraintAnnotation) {}
	
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return Pattern.matches(Constants.EMAIL_REGEX, email);
	}
}
