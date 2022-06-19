package com.theadventure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theadventure.model.BlogPost;

@Repository
public interface BlogPostRepositiory extends JpaRepository<BlogPost, Long>{

}
