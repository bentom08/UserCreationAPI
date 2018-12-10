package com.qa.cv_manager.userapi;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.cv_manager.userapi.persistence.domain.User;
import com.qa.cv_manager.userapi.persistence.domain.UserPOJO;
import com.qa.cv_manager.userapi.persistence.domain.UserRole;
import com.qa.cv_manager.userapi.persistence.repository.UserRepository;
import com.qa.cv_manager.userapi.rest.UserRest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

	@Autowired
	UserRest rest;
	
	@Autowired
	UserRepository repo;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;
	
	private static final String TEST_USERNAME = "ben";
	private static final UserPOJO TEST_USER_POJO = new UserPOJO(TEST_USERNAME, "Password1", "Password1", true, "ROLE_ADMIN");
	private static final UserPOJO UPDATED_TEST_USER_POJO = new UserPOJO(TEST_USERNAME, "Password2", "Password2", true, "ROLE_ADMIN");
	private static final UserRole TEST_USER_ROLE = new UserRole(TEST_USERNAME, "ROLE_ADMIN");
	private static final User TEST_USER = new User(TEST_USERNAME, "Password1", true, TEST_USER_ROLE);
	private static final User UPDATED_TEST_USER = new User(TEST_USERNAME, "Password2", true, TEST_USER_ROLE);
	
	@Test
	public void addUserTest() {
		rest.addUser(TEST_USER_POJO);
		assertEquals(TEST_USER.toString(), repo.findById(TEST_USERNAME).get().toString());
		assertEquals(true, passwordEncoder.matches("Password1", repo.findById(TEST_USERNAME).get().getPassword()));
	}
	
	@Test
	public void updatePasswordTest() {
		rest.updatePassword(UPDATED_TEST_USER_POJO, TEST_USERNAME);
		assertEquals(UPDATED_TEST_USER.toString(), repo.findById(TEST_USERNAME).get().toString());
		assertEquals(true, passwordEncoder.matches("Password2", repo.findById(TEST_USERNAME).get().getPassword()));
	}
	
	@Test
	public void deleteUserTest() {
		rest.deleteUser(TEST_USERNAME);
		assertEquals(Optional.empty(), repo.findById(TEST_USERNAME));
	}
}
