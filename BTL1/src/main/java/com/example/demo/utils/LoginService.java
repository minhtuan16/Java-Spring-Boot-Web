package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("not found");
		}
		
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		
		for(String role : user.getRoles()) {
			list.add(new SimpleGrantedAuthority(role));
		}
		
		//tao user cua security
		// Muc dich la convert thang username tu database len thang username cua security
		org.springframework.security.core.userdetails.User currentUser = 
				new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
		return currentUser;
	}
	
}
