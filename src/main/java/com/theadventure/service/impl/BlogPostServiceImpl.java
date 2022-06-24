package com.theadventure.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theadventure.exception.ResourceNotFoundException;
import com.theadventure.model.BlogPost;
import com.theadventure.model.User;
import com.theadventure.payload.BlogPostDTO;
import com.theadventure.repository.BlogPostRepositiory;
import com.theadventure.repository.UserRepository;
import com.theadventure.service.BlogPostService;

@Service
public class BlogPostServiceImpl implements BlogPostService {

	@Autowired
	private BlogPostRepositiory blogPostRepositiory;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<BlogPost> getAllBlogPosts() {
		return blogPostRepositiory.findAll();
	}
	
	@Override
	public BlogPost createPost(BlogPostDTO blogPostDto) {
		Optional<User> user = userRepository.findById(blogPostDto.getAuthorId());
		BlogPost post = new BlogPost();
		post.setTitle(blogPostDto.getTitle());
		post.setContent(blogPostDto.getContent());
		post.setAuthor(user.get());
		post.setImageBase64(blogPostDto.getImageBase64());
		post.setCreatedDate(blogPostDto.getCreatedDate());
		return blogPostRepositiory.save(post);
	}
	
	@Override
	public BlogPost getPost(Long id) {
		Optional<BlogPost> post = blogPostRepositiory.findById(id);
		if(!post.isPresent()) {
			throw new ResourceNotFoundException("Post", "id", String.valueOf(id));
		}
		return post.get();
	}

	@Override
	public void deletePost(Long id) {
		Optional<BlogPost> post = blogPostRepositiory.findById(id);
		if(!post.isPresent()) {
			throw new ResourceNotFoundException("Post", "id", String.valueOf(id));
		}
		blogPostRepositiory.deleteById(id);;
	}
}
