package com.theadventure.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theadventure.exception.ResourceNotFoundException;
import com.theadventure.model.User;
import com.theadventure.repository.UserRepository;
import com.theadventure.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> user= userRepository.findByEmail(email);
		if(user.isEmpty()) {
			throw new ResourceNotFoundException("User", "email", email);
		}
		return user.get();
	}
}
