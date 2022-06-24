package com.theadventure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.theadventure.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String username);
	
	@Modifying
	@Query("update User u set u.password = ?1 where u.id = ?2")
	void setUserPasswordById(String password, Long userId);
}
