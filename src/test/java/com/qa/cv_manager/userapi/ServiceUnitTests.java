package com.qa.cv_manager.userapi;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.cv_manager.userapi.persistence.domain.User;
import com.qa.cv_manager.userapi.persistence.domain.UserPOJO;
import com.qa.cv_manager.userapi.persistence.domain.UserRole;
import com.qa.cv_manager.userapi.persistence.repository.UserRepository;
import com.qa.cv_manager.userapi.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ServiceUnitTests {

	@InjectMocks
	UserServiceImpl service;
	
	@Mock
	UserRepository repo;
	
	private static final String MOCK_USERNAME = "ben";
	private static final UserPOJO MOCK_USER_POJO = new UserPOJO(MOCK_USERNAME, "Password1", "Password1", true, "ROLE_ADMIN");
	private static final UserRole MOCK_USER_ROLE = new UserRole(MOCK_USERNAME, "ROLE_ADMIN");
	private static final User MOCK_USER = new User(MOCK_USERNAME, "Password1", true, MOCK_USER_ROLE);
	private static final ResponseEntity<Object> RESPONSE_OK = new ResponseEntity<Object>(HttpStatus.OK);
	private static final ResponseEntity<Object> RESPONSE_NOT_FOUND = new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	
	@Test
	public void addUserTest() {
		Mockito.when(repo.save(Mockito.any())).thenReturn(MOCK_USER);
		assertEquals(RESPONSE_OK, service.addUser(MOCK_USER_POJO));
		Mockito.verify(repo).save(Mockito.any());
	}
	
	@Test
	public void updatePasswordTest() {
		Mockito.when(repo.findById(MOCK_USERNAME)).thenReturn(Optional.of(MOCK_USER));
		assertEquals(RESPONSE_OK, service.updatePassword(MOCK_USER_POJO, MOCK_USERNAME));
		Mockito.verify(repo).findById(MOCK_USERNAME);
	}
	
	@Test
	public void updatePasswordNotFoundTest() {
		Mockito.when(repo.findById(MOCK_USERNAME)).thenReturn(Optional.empty());
		assertEquals(RESPONSE_NOT_FOUND, service.updatePassword(MOCK_USER_POJO, MOCK_USERNAME));
		Mockito.verify(repo).findById(MOCK_USERNAME);
	}
	
	@Test
	public void deleteUserTest() {
		Mockito.when(repo.findById(MOCK_USERNAME)).thenReturn(Optional.of(MOCK_USER));
		assertEquals(RESPONSE_OK, service.deleteUser(MOCK_USERNAME));
		Mockito.verify(repo).findById(MOCK_USERNAME);
	}
	
	@Test
	public void deleteUserNotFoundTest() {
		Mockito.when(repo.findById(MOCK_USERNAME)).thenReturn(Optional.empty());
		assertEquals(RESPONSE_NOT_FOUND, service.deleteUser(MOCK_USERNAME));
		Mockito.verify(repo).findById(MOCK_USERNAME);
	}
}
