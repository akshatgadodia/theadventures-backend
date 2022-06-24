package com.theadventure.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.theadventure.exception.ResourceNotFoundException;
import com.theadventure.model.User;
import com.theadventure.service.AuthenticationService;

@Service
public class CustomUserDetails implements UserDetailsService{
	
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = authenticationService.findByEmail(username);
		if(userOptional.isEmpty()) {
			throw new ResourceNotFoundException("User", "email", String.valueOf(username));
		}
		User user = userOptional.get();
		Set<String> role = new HashSet<>();
		role.add("USER");
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthority(role));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthority(Set<String> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}

}
