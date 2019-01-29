package com.naturals.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naturals.persistence.EmployeeRepository;

import lombok.extern.java.Log;

@Service
@Log
public class TAUserService implements UserDetailsService{

	@Autowired
	EmployeeRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("taservice loadUserByUsername start");
		
		return  
			repo.findById(username)
			.filter(m -> m != null)
			.map(m -> new TASecurityUser(m)).get();
		
	}
}
