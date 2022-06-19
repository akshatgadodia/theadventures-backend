package com.theadventure.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.theadventure.model.User;

@Service
public interface AuthenticationService {
	User createUser(User user);
	Optional<User> findByEmail(String username);
}
