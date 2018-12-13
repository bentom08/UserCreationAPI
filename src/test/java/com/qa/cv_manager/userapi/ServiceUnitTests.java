package com.qa.cv_manager.userapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.qa.cv_manager.userapi.persistence.repository.UserRepository;
import com.qa.cv_manager.userapi.service.UserServiceImpl;
import com.qa.cv_manager.userapi.util.constants.Constants;

@RunWith(MockitoJUnitRunner.class)
public class ServiceUnitTests {

	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserRepository repo;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void addUserTest() {
		Mockito.when(repo.save(Mockito.any())).thenReturn(Constants.MOCK_USER);
		Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn(Constants.MOCK_PASSWORD);
		assertEquals(Constants.RESPONSE_OK, service.addUser(Constants.TEST_USER_POJO));
		Mockito.verify(repo).save(Mockito.any());
	}
	
	@Test
	public void updatePasswordTest() {
		Mockito.when(repo.findById(Constants.MOCK_USERNAME)).thenReturn(Optional.of(Constants.MOCK_USER));
		Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn(Constants.MOCK_PASSWORD);
		assertEquals(Constants.RESPONSE_OK, service.updatePassword(Constants.TEST_USER_POJO, Constants.MOCK_USERNAME));
		Mockito.verify(repo).findById(Constants.MOCK_USERNAME);
	}
	
	@Test
	public void updatePasswordNotFoundTest() {
		Mockito.when(repo.findById(Constants.MOCK_USERNAME)).thenReturn(Optional.empty());
		assertEquals(Constants.RESPONSE_NOT_FOUND, service.updatePassword(Constants.TEST_USER_POJO, Constants.MOCK_USERNAME));
		Mockito.verify(repo).findById(Constants.MOCK_USERNAME);
	}
	
	@Test
	public void deleteUserTest() {
		Mockito.when(repo.findById(Constants.MOCK_USERNAME)).thenReturn(Optional.of(Constants.MOCK_USER));
		assertEquals(Constants.RESPONSE_OK, service.deleteUser(Constants.MOCK_USERNAME));
		Mockito.verify(repo).findById(Constants.MOCK_USERNAME);
	}
	
	@Test
	public void deleteUserNotFoundTest() {
		Mockito.when(repo.findById(Constants.MOCK_USERNAME)).thenReturn(Optional.empty());
		assertEquals(Constants.RESPONSE_NOT_FOUND, service.deleteUser(Constants.MOCK_USERNAME));
		Mockito.verify(repo).findById(Constants.MOCK_USERNAME);
	}
	
	@Test
	public void enableAccountTest() {
		Mockito.when(repo.findById(Constants.MOCK_USERNAME)).thenReturn(Optional.of(Constants.MOCK_USER));
		assertEquals(Constants.RESPONSE_OK, service.enableAccount(Constants.MOCK_USERNAME));
		Mockito.verify(repo, times(2)).findById(Constants.MOCK_USERNAME);
	}
	
	@Test
	public void enableAccountNotFoundTest() {
		Mockito.when(repo.findById(Constants.MOCK_USERNAME)).thenReturn(Optional.empty());
		assertEquals(Constants.RESPONSE_NOT_FOUND, service.enableAccount(Constants.MOCK_USERNAME));
		Mockito.verify(repo).findById(Constants.MOCK_USERNAME);
	}
	
	@Test
	public void disableAccountTest() {
		Mockito.when(repo.findById(Constants.MOCK_USERNAME)).thenReturn(Optional.of(Constants.MOCK_USER));
		assertEquals(Constants.RESPONSE_OK, service.disableAccount(Constants.MOCK_USERNAME));
		Mockito.verify(repo, times(2)).findById(Constants.MOCK_USERNAME);
	}
	
	@Test
	public void disableAccountNotFoundTest() {
		Mockito.when(repo.findById(Constants.MOCK_USERNAME)).thenReturn(Optional.empty());
		assertEquals(Constants.RESPONSE_NOT_FOUND, service.disableAccount(Constants.MOCK_USERNAME));
		Mockito.verify(repo).findById(Constants.MOCK_USERNAME);
	}
}
