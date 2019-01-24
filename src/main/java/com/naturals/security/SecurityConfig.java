package com.naturals.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		log.info("Security configuration");
		
		http.authorizeRequests()
					.antMatchers("/guest/**").permitAll()
					.antMatchers("/ta/**").hasRole("MANAGER")
					.antMatchers("/ta/**").hasRole("ADMIN");
		http.formLogin().loginPage("/login");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		log.info("build auth global");
		
	}
	
}
