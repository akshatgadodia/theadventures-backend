package com.theadventure.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.theadventure.model.User;
import com.theadventure.payload.ChangePasswordDTO;

@Service
public interface UserService {
	
	List<User> getAllUsers();
	User getUserByEmail(String email);
	String changePassword(ChangePasswordDTO changePasswordDto);
	User getUserById(Long id);
}
