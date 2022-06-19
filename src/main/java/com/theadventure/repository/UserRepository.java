package com.theadventure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theadventure.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String username);
}
