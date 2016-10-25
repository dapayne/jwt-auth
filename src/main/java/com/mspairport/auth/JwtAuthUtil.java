package com.mspairport.auth;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class JwtAuthUtil {

	
	public final String generateToken(final String secretKey) throws UnsupportedEncodingException {
		
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("name", "MSPMAC");
		
		String compactJws = Jwts.builder()
				.setClaims(claims)
				.setSubject("mspmac-12345")
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.setHeaderParam("typ", "JWT")				
				.compact();
		
		return compactJws;
	}

	
	public final String generateSecretKey() {
		Key key = MacProvider.generateKey(SignatureAlgorithm.HS256);
		return TextCodec.BASE64.encode(key.getEncoded());		
	}
	
	public boolean validateToken(final String token, final String secretKey) {
		
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.getSubject().equals("mspmac-12345");		
		
	}
}
