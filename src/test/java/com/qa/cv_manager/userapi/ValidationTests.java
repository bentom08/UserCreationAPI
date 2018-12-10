package com.qa.cv_manager.userapi;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.cv_manager.userapi.persistence.domain.UserPOJO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ValidationTests {

	@Autowired
	private Validator validator;
	
	private static final UserPOJO CORRECT_TEST_USER = new UserPOJO("ben", "Password1", "Password1", true, "ROLE_ADMIN");
	private static final UserPOJO SPECIAL_CHAR_TEST_USER = new UserPOJO("ben", "password1.", "password1.", true, "ROLE_ADMIN");
	private static final UserPOJO DIFFERENT_PS_USER = new UserPOJO("ben", "Password1", "Password2", true, "ROLE_ADMIN");
	private static final UserPOJO SHORT_PS_USER = new UserPOJO("ben", "Passwo1", "Passwo1", true, "ROLE_ADMIN");
	private static final UserPOJO NOCAPS_PS_USER = new UserPOJO("ben", "password1", "password1", true, "ROLE_ADMIN");
	private static final UserPOJO NONUMBER_PS_USER = new UserPOJO("ben", "Password", "Password", true, "ROLE_ADMIN");
	
	@Test
	public void validObjectTest() {
		Set<ConstraintViolation<UserPOJO>> violations = validator.validate(CORRECT_TEST_USER);
		assertEquals(true, violations.isEmpty());
		violations = validator.validate(SPECIAL_CHAR_TEST_USER);
		assertEquals(true, violations.isEmpty());
	}
	
	@Test
	public void passwordMatchesTest() {
		Set<ConstraintViolation<UserPOJO>> violations = validator.validate(DIFFERENT_PS_USER);
		assertEquals(false, violations.isEmpty());
	}
	
	@Test
	public void passwordLengthTest() {
		Set<ConstraintViolation<UserPOJO>> violations = validator.validate(SHORT_PS_USER);
		assertEquals(false, violations.isEmpty());
	}
	
	@Test
	public void notEnoughCharTypesTest() {
		Set<ConstraintViolation<UserPOJO>> violations = validator.validate(NOCAPS_PS_USER);
		assertEquals(false, violations.isEmpty());
		violations = validator.validate(NONUMBER_PS_USER);
		assertEquals(false, violations.isEmpty());
	}
}
