package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder()); // dung thu vien cua springboot 2 de su dung password
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
//			.antMatchers("/department/**").hasRole("Admin") //permitAll() la khong can dang nhap

//			.antMatchers("/employee/**").authenticated() //authenticated() la phai dang nhap vao
			
			.antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
			.anyRequest().permitAll()
			.and().csrf().disable()
			
			.formLogin()
			.loginPage("/dang-nhap")
			.loginProcessingUrl("/login")
			.failureUrl("/dang-nhap?err=true")
			.defaultSuccessUrl("/user/search", true)
			.and().httpBasic().and()
			.exceptionHandling().accessDeniedPage("/login");
	}
}
