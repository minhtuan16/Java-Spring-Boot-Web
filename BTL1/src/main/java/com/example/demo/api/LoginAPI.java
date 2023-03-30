//package com.example.demo.api;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.dto.TokenDTO;
//import com.example.demo.service.JwtTokenProvider;
//
//@RestController
//public class LoginAPI {
//
//	@Autowired
//	UserDetailsService userDetailsService;
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;
//	
//	@PostMapping("/api/login")
//	public TokenDTO login(@RequestParam(required = true, name = "username") String username,
//			@RequestParam(required = true, name = "password") String password) {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//			return jwtTokenProvider.createToken(username);
//		} catch (AuthenticationException e) {
//			throw new UsernameNotFoundException("Invalid username/password supplied");
//		}
//	}
//}
