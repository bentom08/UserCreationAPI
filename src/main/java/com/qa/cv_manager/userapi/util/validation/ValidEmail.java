package com.qa.cv_manager.userapi.util.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.qa.cv_manager.userapi.util.constants.Constants;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEmailValidator.class)
@Documented
public @interface ValidEmail {
	String message() default Constants.INVALID_EMAIL_ERROR;
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
