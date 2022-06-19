package com.theadventure.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
public class AuthUserSignInDTO {
	
	
	@Email
	private String email;
	
	@NotBlank
	private String password;
	
	public AuthUserSignInDTO() {

	}
	
	public AuthUserSignInDTO( String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
