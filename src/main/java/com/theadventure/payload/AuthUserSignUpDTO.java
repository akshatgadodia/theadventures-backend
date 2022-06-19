package com.theadventure.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class AuthUserSignUpDTO {
	
	@NotBlank
	@Size(min=1,max=100)
	private String name;
	
	@Email
	private String email;
	
	@NotBlank
	@Size(min=8)
	private String password;
	
	public AuthUserSignUpDTO(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public AuthUserSignUpDTO() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
