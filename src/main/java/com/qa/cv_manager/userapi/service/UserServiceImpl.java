package com.qa.cv_manager.userapi.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qa.cv_manager.userapi.persistence.domain.User;
import com.qa.cv_manager.userapi.persistence.domain.UserPOJO;
import com.qa.cv_manager.userapi.persistence.domain.UserRole;
import com.qa.cv_manager.userapi.persistence.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repo;
	
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	

	public ResponseEntity<Object> addUser(UserPOJO user) {
		UserRole role = new UserRole(user.getUsername(), user.getRole());
		
		User storedUser = new User(user.getUsername(),
									passwordEncoder.encode(user.getPassword()),
									user.isEnabled(),
									role);

		repo.save(storedUser);
		
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Object> updatePassword(UserPOJO user, String username) {
		Optional<User> optionalUser = repo.findById(username);
		
		if (!optionalUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		UserRole role = new UserRole(user.getUsername(), user.getRole());
		
		User storedUser = new User(user.getUsername(),
				passwordEncoder.encode(user.getPassword()),
				user.isEnabled(),
				role);
		
		storedUser.setUsername(username);
		repo.save(storedUser);
		
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Object> deleteUser(String username) {
		Optional<User> user = repo.findById(username);
		
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
			
		repo.deleteById(username);
		
		return ResponseEntity.ok().build();
	}

}
