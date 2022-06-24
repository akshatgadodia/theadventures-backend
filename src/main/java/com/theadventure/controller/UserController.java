package com.theadventure.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.theadventure.model.BlogPost;
import com.theadventure.model.User;
import com.theadventure.payload.BlogPostDTO;
import com.theadventure.payload.ChangePasswordDTO;
import com.theadventure.payload.UserDTO;
import com.theadventure.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api("REST API's for User")
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	private BlogPostDTO mapToBlogPostDto(BlogPost blogPost) {
		BlogPostDTO blogPostDTO = new BlogPostDTO(blogPost.getPostId(),blogPost.getTitle(),blogPost.getContent(),
				blogPost.getAuthor().getName(),blogPost.getImageBase64(),blogPost.getCreatedDate(),blogPost.getAuthor().getUserId());
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
	
	@ApiOperation(value= "REST API to get update user password")
	@PatchMapping
	public ResponseEntity<String> changeUserPassword(@RequestBody ChangePasswordDTO changePasswordDto){
		return ResponseEntity.ok(userService.changePassword(changePasswordDto));
	}
	
	@ApiOperation(value= "REST API to get all users")
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> list = userService.getAllUsers().stream().map(this::mapToDto).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
	@ApiOperation(value= "REST API to get user by email")
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
		UserDTO userDto = mapToDto(userService.getUserById(id));
		return ResponseEntity.ok(userDto);
	}

}
