package com.theadventure.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.theadventure.exception.MessageException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtAuthenticationTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-millisecond}")
	private int jwtExpiration;
	
	//Generate JWT Token
	public String generateToken(Authentication auth) {
		String username = auth.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime()+jwtExpiration);
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact(); 
		return token;
	}
	
	//Get username from JWT 
	public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	//Validate JWT
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}
		catch(SignatureException ex) {
			throw new MessageException("Invalid JWT Signature");
		}
		catch(MalformedJwtException ex) {
			throw new MessageException("Invalid JWT token");
		}
		catch(ExpiredJwtException ex) {
			throw new MessageException("Expired JWT Token");
		}
		catch(UnsupportedJwtException ex) {
			throw new MessageException("Unsupported JWT Token");
		}
		catch(IllegalArgumentException ex) {
			throw new MessageException("JWT claims string is empty");
		}
	}
	
}
