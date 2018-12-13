package com.qa.cv_manager.userapi;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.cv_manager.userapi.persistence.domain.User;
import com.qa.cv_manager.userapi.persistence.repository.UserRepository;
import com.qa.cv_manager.userapi.rest.UserRest;
import com.qa.cv_manager.userapi.util.constants.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrationTests {

	@Autowired
	private UserRest rest;
	
	@Autowired
	private UserRepository repo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private static final User UPDATED_TEST_USER = new User(Constants.MOCK_USERNAME, Constants.UPDATED_PASSWORD, true, Constants.MOCK_USER_ROLE, Constants.MOCK_USER_EMAIL);
	
	@Test
	public void addUserTest() {
		rest.addUser(Constants.TEST_USER_POJO);
		assertEquals(Constants.MOCK_USER.toString(), repo.findById(Constants.MOCK_USERNAME).get().toString());
		assertEquals(true, passwordEncoder.matches(Constants.MOCK_PASSWORD, repo.findById(Constants.MOCK_USERNAME).get().getPassword()));
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void bUpdatePasswordTest() {
		rest.updatePassword(Constants.UPDATED_TEST_USER_POJO, Constants.MOCK_USERNAME);
		assertEquals(UPDATED_TEST_USER.toString(), repo.findById(Constants.MOCK_USERNAME).get().toString());
		assertEquals(true, passwordEncoder.matches(Constants.UPDATED_PASSWORD, repo.findById(Constants.MOCK_USERNAME).get().getPassword()));
	}
	
	@Test
	public void cDisableAccountTest() {
		assertEquals(Constants.RESPONSE_OK, rest.disableAccount(Constants.MOCK_USERNAME));
		assertEquals(false, repo.findById(Constants.MOCK_USERNAME).get().isEnabled());
	}
	
	@Test
	public void dEnableAccountTest() {
		assertEquals(Constants.RESPONSE_OK, rest.enableAccount(Constants.MOCK_USERNAME));
		assertEquals(true, repo.findById(Constants.MOCK_USERNAME).get().isEnabled());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void eDeleteUserTest() {
		rest.deleteUser(Constants.MOCK_USERNAME);
		assertEquals(Optional.empty(), repo.findById(Constants.MOCK_USERNAME));
	}
}
