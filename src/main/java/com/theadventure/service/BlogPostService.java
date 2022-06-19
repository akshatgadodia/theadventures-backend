package com.theadventure.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.theadventure.model.BlogPost;
import com.theadventure.payload.BlogPostDTO;

@Service
public interface BlogPostService {
	
	List<BlogPost> getAllBlogPosts();
	BlogPost createPost(BlogPostDTO blogPostDto);
	BlogPost getPost(Long id);
}
