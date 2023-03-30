package com.example.demo.utils;
//package com.example.demo.service;
//
//import java.util.Base64;
//import java.util.Date;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.dto.TokenDTO;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
////import jmaster.io.exception.JwtCustomException;
////import jmaster.io.model.TokenDTO;
//
//@Component
//public class JwtTokenProvider {
//	/**
//	 * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key
//	 * here. Ideally, in a microservices environment, this key would be kept on a
//	 * config-server.
//	 */
//	@Value("${security.jwt.token.secret-key:secret-key}")
//	private String secretKey;
//
//	// thoi gian token het han ( o day la 1h )
//	@Value("${security.jwt.token.expire-length:3600000}")
//	private long validityInMilliseconds = 3600000; // 1h
//
//	@Autowired
//	private UserDetailsService myUserDetails;
//
//	@PostConstruct
//	protected void init() {
//		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//	}
//
//	// ham tao token
//	public TokenDTO createToken(String username) {
//		Claims claims = Jwts.claims().setSubject(username);
//		Date now = new Date();
//		Date validity = new Date(now.getTime() + validityInMilliseconds);
//		String accessToken = Jwts.builder()//
//				.setClaims(claims)//
//				.setIssuedAt(now)//
//				.setExpiration(validity)//
//				.signWith(SignatureAlgorithm.HS256, secretKey)//
//				.compact();
//		TokenDTO authenDTO = new TokenDTO();
////		authenDTO.setExpirationTime(validityInMilliseconds);
//		authenDTO.setAccessToken(accessToken);
//		return authenDTO;
//	}
//
//	
//	public Authentication getAuthentication(String token) {
//		UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
//		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//	}
//
//	// ham lay ra username
//	public String getUsername(String token) {
//		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//	}
//
//	public String resolveToken(HttpServletRequest req) {
//		String bearerToken = req.getHeader("Authorization");
//		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//			return bearerToken.substring(7);
//		}
//		return null;
//	}
//
//	// xem token con han hay khong
//	public boolean validateToken(String token) {
//		try {
//			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//			return true;
//		} catch (JwtException | IllegalArgumentException e) {
//			throw new UsernameNotFoundException("Expired or invalid JWT token");
//		}
//	}
//}