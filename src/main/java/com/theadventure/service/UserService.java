package com.theadventure.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.theadventure.model.User;

@Service
public interface UserService {
	
	List<User> getAllUsers();
	User getUserByEmail(String email);
}
