package com.theadventure.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theadventure.model.BlogPost;
import com.theadventure.payload.BlogPostDTO;
import com.theadventure.service.BlogPostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
	
	
	@Autowired
	private BlogPostService postService;
	
	private BlogPostDTO mapToDto(BlogPost blogPost) {
		BlogPostDTO blogPostDTO = new BlogPostDTO(blogPost.getPostId(),blogPost.getTitle(),blogPost.getContent(),
				blogPost.getAuthor().getName(),blogPost.getImageBase64(),blogPost.getCreatedDate());
		return blogPostDTO;
	}
	
	@GetMapping
	public ResponseEntity<List<BlogPostDTO>> getAllBlogPosts(){
		List<BlogPostDTO> list = postService.getAllBlogPosts().stream().map(this::mapToDto).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BlogPostDTO>getPostById(@PathVariable Long id){
		BlogPostDTO blogPostDto = mapToDto(postService.getPost(id));
		return ResponseEntity.ok(blogPostDto);
	}

	@PostMapping
	public ResponseEntity<BlogPostDTO> createPost(@Valid @RequestBody BlogPostDTO blogPostDto){
		System.out.println(blogPostDto);
		BlogPostDTO blogPostDTO = mapToDto(postService.createPost(blogPostDto));
		return new ResponseEntity<>(blogPostDTO,HttpStatus.CREATED);
	}
}
