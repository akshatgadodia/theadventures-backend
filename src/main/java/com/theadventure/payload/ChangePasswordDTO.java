package com.theadventure.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;


@Component
public class ChangePasswordDTO {
	
	@Override
	public String toString() {
		return "ChangePasswordDTO [userId=" + userId + ", currentPassword=" + currentPassword + ", newPassword="
				+ newPassword + "]";
	}
	
	@NotBlank
	@NotNull
	private Long userId;
	
	private String currentPassword;
	
	private String newPassword;
	
	
	public ChangePasswordDTO() {
		super();
	}
	
	public ChangePasswordDTO(Long userId, String currentPassword, String newPassword) {
		super();
		this.userId = userId;
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
