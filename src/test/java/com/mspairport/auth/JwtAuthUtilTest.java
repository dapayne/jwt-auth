package com.mspairport.auth;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtAuthUtilTest {

	@Test
	public void testAuthenticate() throws UnsupportedEncodingException {
 
		JwtAuthUtil jwtUtil = new JwtAuthUtil();
		
		final String secretKey = jwtUtil.generateSecretKey();
		
		final String token = jwtUtil.generateToken(secretKey);
		
		assertTrue(jwtUtil.validateToken(token, secretKey));
		
		
	}

}
