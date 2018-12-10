package com.qa.cv_manager.userapi.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.cv_manager.userapi.persistence.domain.UserPOJO;
import com.qa.cv_manager.userapi.service.UserService;
import com.qa.cv_manager.userapi.service.UserServiceImpl;

@RestController
public class UserRest {

	@Autowired
	UserService service;
	
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody @Valid UserPOJO user) {
		return service.addUser(user);
	}
	
	@PutMapping("/updateUser/{username}")
	public ResponseEntity<Object> updatePassword(@RequestBody @Valid UserPOJO user, @PathVariable String username) {
		return service.updatePassword(user, username);
	}
	
	@DeleteMapping("/deleteUser/{username}")
	public ResponseEntity<Object> deleteUser(@PathVariable String username) {
		return service.deleteUser(username);
	}
}
