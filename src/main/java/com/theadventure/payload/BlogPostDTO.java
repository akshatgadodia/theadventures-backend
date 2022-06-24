package com.theadventure.payload;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class BlogPostDTO {
	
	Long postId;
	
	@Size(min=1)
	private String title;

	@Size(min=1)
	private String content;
	
	@NotNull
	@NotBlank
	private String authorName;
	
	private Long authorId;
	
	private String imageBase64;
	
	Date createdDate;
	
	public BlogPostDTO() {
		super();
	}

	public BlogPostDTO(Long postId, String title, String content, String authorName,String imageBase64, Date createdDate, Long authorId) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.authorName = authorName;
		this.createdDate=createdDate;
		this.imageBase64=imageBase64;
		this.authorId=authorId;
	}


	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

}
