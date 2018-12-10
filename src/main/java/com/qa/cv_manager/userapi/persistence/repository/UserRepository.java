package com.qa.cv_manager.userapi.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.cv_manager.userapi.persistence.domain.User;

public interface UserRepository extends JpaRepository<User, String>{
}
