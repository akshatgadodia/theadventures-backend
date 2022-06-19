package com.theadventure.controller;

import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theadventure.model.User;
import com.theadventure.payload.AuthUserSignInDTO;
import com.theadventure.payload.AuthUserSignUpDTO;
import com.theadventure.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private ModelMapper modelMapper; 
	
	private User maptoUser(AuthUserSignUpDTO authUserSignUpDTO) {
		return modelMapper.map(authUserSignUpDTO,User.class);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<Map<String,String>> signIn(@Valid @RequestBody AuthUserSignInDTO authUserSignInDTO){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
				(authUserSignInDTO.getEmail(), authUserSignInDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Map<String,String> signInDetails = Map.of("userid","uid",
				"name","name",
				"jwt","jwt");
		return new ResponseEntity<>(signInDetails,HttpStatus.CREATED);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Map<String,String>> signUp(@Valid @RequestBody AuthUserSignUpDTO authUserSignUpDTO){
		User user = maptoUser(authUserSignUpDTO);
		User createdUser = authenticationService.createUser(user);
		Map<String,String> result = Map.of("userId",String.valueOf(createdUser.getUserId()),
				"email",user.getEmail(),
				"name",user.getName());
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
}
