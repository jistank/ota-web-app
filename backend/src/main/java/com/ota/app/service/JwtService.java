package com.ota.app.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET = "bXlzdXBlcnNlY3JldGtleW15c2VjdXJlc2VjcmV0a2V5bXlzdXBlcnNlY3JldGtleQ=="; // 256-bit key (32 bytes) encoded in Base64
	
	public String generateToken(String email) {
		Map<String, Object> claims = Map.of("email", email);

		return Jwts.builder()
			    .claims(Map.of("email", email))
			    .subject(email)
			    .issuedAt(new Date())
			    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
			    .signWith(getKey())
			    .compact();
	}

	public String generateSecretKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey secretKey = keyGen.generateKey();
			System.out.println("Secret Key : " + secretKey.toString());
			return Base64.getEncoder().encodeToString(secretKey.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error generating secret key", e);
		}
	}

	private Key getKey() {
	    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
	    return Keys.hmacShaKeyFor(keyBytes);
	}


	public String extractUsername(String token) {
	    return Jwts.parser()
	        .verifyWith((SecretKey) getKey())
	        .build()
	        .parseSignedClaims(token)
	        .getPayload()
	        .getSubject();
	}
	
	public boolean validateToken(String token) {
	    try {
	        Jwts.parser()
	            .verifyWith((SecretKey) getKey())
	            .build()
	            .parseSignedClaims(token);
	        return true;
	    } catch (JwtException | IllegalArgumentException e) {
	        return false;
	    }
	}
	
}
