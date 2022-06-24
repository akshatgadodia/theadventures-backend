package com.theadventure.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theadventure.model.User;
import com.theadventure.payload.AuthUserSignInDTO;
import com.theadventure.payload.AuthUserSignUpDTO;
import com.theadventure.security.jwt.JwtAuthenticationTokenProvider;
import com.theadventure.service.AuthenticationService;
import com.theadventure.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="REST API's for Authentication")
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider;
	
	@Autowired 
	private ModelMapper modelMapper; 
	
	private User maptoUser(AuthUserSignUpDTO authUserSignUpDTO) {
		return modelMapper.map(authUserSignUpDTO,User.class);
	}
	
	@ApiOperation(value= "REST API to signin user")
	@PostMapping("/signin")
	public ResponseEntity<Map<String,String>> signIn(@Valid @RequestBody AuthUserSignInDTO authUserSignInDTO){
		User user = userService.getUserByEmail(authUserSignInDTO.getEmail());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
				(authUserSignInDTO.getEmail(), authUserSignInDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtAuthenticationTokenProvider.generateToken(authentication);
		Map<String,String> signInDetails = new HashMap<String,String>();/*Map.of("userId",String.valueOf(user.getUserId()),
				"userName",user.getName(),
				"userEmail",user.getEmail(),
				"accessToken",token,
				"tokenType","Bearer");*/
		signInDetails.put("userId",String.valueOf(user.getUserId()));
		signInDetails.put("userName",user.getName());
		signInDetails.put("userEmail",user.getEmail());
		signInDetails.put(	"accessToken",token);
		signInDetails.put("tokenType","Bearer");
		return new ResponseEntity<>(signInDetails,HttpStatus.CREATED);
	}
	
	@ApiOperation(value= "REST API to signup user")
	@PostMapping("/signup")
	public ResponseEntity<Map<String,String>> signUp(@Valid @RequestBody AuthUserSignUpDTO authUserSignUpDTO){
		User user = maptoUser(authUserSignUpDTO);
		User createdUser = authenticationService.createUser(user);
		Map<String,String> result = new HashMap<String,String>();/*Map.of("userId",String.valueOf(createdUser.getUserId()),
				"email",user.getEmail(),
				"name",user.getName());*/
		result.put("userId",String.valueOf(createdUser.getUserId()));
		result.put("email",user.getEmail());
		result.put("name",user.getName());
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
}
