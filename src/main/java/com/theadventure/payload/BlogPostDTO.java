package com.theadventure.payload;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class BlogPostDTO {
	
	Long postId;
	
	@Size(min=1,max=50)
	private String title;

	@Size(min=10)
	private String content;
	
	@NotNull
	@NotBlank
	private String author;
	
	private String imageBase64;
	
	Date createdDate;
	public BlogPostDTO() {
		super();
	}

	public BlogPostDTO(Long postId, String title, String content, String author,String imageBase64, Date createdDate) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.author = author;
		this.createdDate=createdDate;
		this.imageBase64=imageBase64;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "BlogPostDTO [postId=" + postId + ", title=" + title + ", content=" + content + ", author=" + author
				+ ", imageBase64=" + imageBase64 + "]";
	}
	
	
}
