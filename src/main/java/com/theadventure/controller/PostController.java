package com.theadventure.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theadventure.model.BlogPost;
import com.theadventure.payload.BlogPostDTO;
import com.theadventure.service.BlogPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("REST API's for Blog Post")
@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin(origins = "*")
public class PostController {
	
	
	@Autowired
	private BlogPostService postService;
	
	private BlogPostDTO mapToDto(BlogPost blogPost) {
		BlogPostDTO blogPostDTO = new BlogPostDTO(blogPost.getPostId(),blogPost.getTitle(),blogPost.getContent(),
				blogPost.getAuthor().getName(),blogPost.getImageBase64(),blogPost.getCreatedDate(),blogPost.getAuthor().getUserId());
		return blogPostDTO;
	}
	
	@ApiOperation(value= "REST API to get all posts")
	@GetMapping
	public ResponseEntity<List<BlogPostDTO>> getAllBlogPosts(){
		List<BlogPostDTO> list = postService.getAllBlogPosts().stream().map(this::mapToDto).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
	@ApiOperation(value= "REST API to get post by id")
	@GetMapping("/{id}")
	public ResponseEntity<BlogPostDTO>getPostById(@PathVariable Long id){
		BlogPostDTO blogPostDto = mapToDto(postService.getPost(id));
		return ResponseEntity.ok(blogPostDto);
	}

	@ApiOperation(value= "REST API to create post")
	@PostMapping
	public ResponseEntity<BlogPostDTO> createPost(@Valid @RequestBody BlogPostDTO blogPostDto){
		BlogPostDTO blogPostDTO = mapToDto(postService.createPost(blogPostDto));
		return new ResponseEntity<>(blogPostDTO,HttpStatus.CREATED);
	}
	
	@ApiOperation(value= "REST API to delete post")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id){
		System.out.println("Del VAR");
		postService.deletePost(id);
		return ResponseEntity.ok("Post with id : "+id+" deleted successfully");
	}
}
