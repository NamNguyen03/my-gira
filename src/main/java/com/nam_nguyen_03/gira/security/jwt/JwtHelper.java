package com.nam_nguyen_03.gira.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@PropertySources({ @PropertySource("classpath:application-config.properties") })
public class JwtHelper {
    @Value("${my.config.spring.security.jwt.key}")
	private String key;
	private String prefix  = "Bearer ";
    @Value("${my.config.spring.security.jwt.expiration}")
    private int expiration;
	
	public String generateJwtToken(String username) {
		
		Date currentDate = new Date();
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(currentDate)
				.setExpiration(new Date(currentDate.getTime() + expiration))
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}

	public boolean validate(String token) {

		if(token == null || token.length() == 0){
			return false;
		}

		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch(UnsupportedJwtException e1) {
			log.error("JWT token is not supported: {}", e1);
		} catch(MalformedJwtException e2) {
			log.error("Invalid Token: {}", e2);
		} catch(SignatureException e3) {
			log.error("Invalid signature: {}", e3);
		} catch(ExpiredJwtException e4) {
			log.error("JWT is expired: {}", e4);
		} catch(IllegalArgumentException e5) {
			log.error("JWT Claims is empty: {}", e5);
		}
		
		return false;
	}
	
	public String getToken(HttpServletRequest request) {
		String jwt = request.getHeader("Authorization");
		
		if(jwt == null)
			return jwt;
		
		return jwt.substring(prefix.length(), jwt.length());
	}
	
	public String getUsername(String token) {
		return Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}

