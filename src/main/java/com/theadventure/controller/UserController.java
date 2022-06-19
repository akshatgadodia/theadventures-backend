package com.theadventure.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theadventure.model.BlogPost;
import com.theadventure.model.User;
import com.theadventure.payload.BlogPostDTO;
import com.theadventure.payload.UserDTO;
import com.theadventure.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	private BlogPostDTO mapToBlogPostDto(BlogPost blogPost) {
		BlogPostDTO blogPostDTO = new BlogPostDTO(blogPost.getPostId(),blogPost.getTitle(),blogPost.getContent(),
				blogPost.getAuthor().getName(),blogPost.getImageBase64(),blogPost.getCreatedDate());
		return blogPostDTO;
	}
	
	private UserDTO mapToDto(User user) {
		//return modelMapper.map(user,UserDTO.class);
		UserDTO userDto = new UserDTO();
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setUserId(user.getUserId());
		Set<BlogPostDTO> blogPostSet = user.getPosts().stream().map(this::mapToBlogPostDto).collect(Collectors.toSet());
		userDto.setPosts(blogPostSet);
		return userDto;
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> list = userService.getAllUsers().stream().map(this::mapToDto).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<UserDTO>getUserById(@PathVariable String email){
		UserDTO userDto = mapToDto(userService.getUserByEmail(email));
		return ResponseEntity.ok(userDto);
	}
	
}
