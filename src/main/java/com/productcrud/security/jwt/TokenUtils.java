/**
 * 
 */
package com.productcrud.security.jwt;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
	
	private static final String ACCESS_TOKEN_SECRET = "micProductCrudMercadonaTokenJwtSecret2023";
	
	private static final Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
	
	public static String createToken(String username) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		
		Map<String, Object> data = new HashMap<>();
		data.put("username", username);
		
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(expirationDate)
				.addClaims(data)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
				.compact();
		
	}
	
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String username = claims.getSubject();
			
			return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
		} catch (JwtException e) {
			return null;
		}
		
		
		
	}

}
