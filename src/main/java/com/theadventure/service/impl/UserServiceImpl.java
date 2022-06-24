package com.theadventure.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theadventure.exception.ResourceNotFoundException;
import com.theadventure.model.User;
import com.theadventure.payload.ChangePasswordDTO;
import com.theadventure.repository.UserRepository;
import com.theadventure.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
	
	@Override
	@Transactional
	public String changePassword(ChangePasswordDTO changePasswordDto) {
		//System.out.println("User Id is "+changePasswordDto.getUserId());
		Optional<User> user= userRepository.findById(changePasswordDto.getUserId());
		if(user.isEmpty()) {
			throw new ResourceNotFoundException("User", "id", String.valueOf(changePasswordDto.getUserId()));
		}
		userRepository.setUserPasswordById(passwordEncoder.encode(changePasswordDto.getNewPassword()), changePasswordDto.getUserId());
		return "Password Updated Successfully";
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> user= userRepository.findById(id);
		if(user.isEmpty()) {
			throw new ResourceNotFoundException("User", "id", String.valueOf(id));
		}
		return user.get();
	}
}
