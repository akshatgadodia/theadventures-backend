package com.theadventure.payload;

import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {
	
	private Long userId;
	private String name;
	private String email;
	private Set<BlogPostDTO> posts;
	
	public UserDTO() {
	}
	
	public UserDTO(Long userId, String name, String email, Set<BlogPostDTO> posts) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.posts = posts;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Set<BlogPostDTO> getPosts() {
		return posts;
	}

	public void setPosts(Set<BlogPostDTO> posts) {
		this.posts = posts;
	}
	
}
