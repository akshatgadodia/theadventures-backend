package com.theadventure.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.theadventure.exception.UserAlreadyExistsException;
import com.theadventure.model.User;
import com.theadventure.repository.UserRepository;
import com.theadventure.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public User createUser(User user) {
		//User newUser = new User(user.getName(),user.getEmail(),user.getPassword());
		boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
		if(userExists) {
			throw new UserAlreadyExistsException("User", "email", String.valueOf(user.getEmail()));
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		user.setRole("USER");
		return userRepository.save(user);
		
	}

	@Override
	public Optional<User> findByEmail(String username) {
		return userRepository.findByEmail(username);
	}
}
