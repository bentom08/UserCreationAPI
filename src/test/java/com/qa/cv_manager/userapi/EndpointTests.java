package com.qa.cv_manager.userapi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.cv_manager.userapi.persistence.domain.UserPOJO;
import com.qa.cv_manager.userapi.rest.UserRest;
import com.qa.cv_manager.userapi.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EndpointTests {
	
	@InjectMocks
	UserRest rest;
	
	@Mock
	UserServiceImpl service;
	
	private static final String MOCK_USERNAME = "ben";
	private static final UserPOJO MOCK_USER = new UserPOJO(MOCK_USERNAME, "Password1", "Password1", true, "ROLE_ADMIN");
	private static final ResponseEntity<Object> MOCK_RESPONSE = new ResponseEntity<>(HttpStatus.OK);

	@Test
	public void addUserTest() {
		Mockito.when(service.addUser(MOCK_USER)).thenReturn(MOCK_RESPONSE);
		assertEquals(MOCK_RESPONSE, rest.addUser(MOCK_USER));
		Mockito.verify(service).addUser(MOCK_USER);
	}
	
	@Test
	public void updatePasswordTest() {
		Mockito.when(service.updatePassword(MOCK_USER, MOCK_USERNAME)).thenReturn(MOCK_RESPONSE);
		assertEquals(MOCK_RESPONSE, rest.updatePassword(MOCK_USER, MOCK_USERNAME));
		Mockito.verify(service).updatePassword(MOCK_USER, MOCK_USERNAME);
	}
	
	@Test
	public void deleteUserTest() {
		Mockito.when(service.deleteUser(MOCK_USERNAME)).thenReturn(MOCK_RESPONSE);
		assertEquals(MOCK_RESPONSE, rest.deleteUser(MOCK_USERNAME));
		Mockito.verify(service).deleteUser(MOCK_USERNAME);
	}
}
