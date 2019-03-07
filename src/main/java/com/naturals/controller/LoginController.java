package com.naturals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naturals.domain.Employee;
import com.naturals.persistence.EmployeeRepository;

import lombok.extern.java.Log;

@Log
@Controller
public class LoginController {

	@Autowired
	EmployeeRepository repo;
	
	@Autowired
	PasswordEncoder pe;

	@GetMapping("/login")
	public void login() {
		log.info("login CALLED");
	}

	@GetMapping("/accessDenied")
	public void accessDenied() {
		log.info("accessDenied CALLED");

	}

	@GetMapping("/logout")
	public void logout() {
		log.info("logout CALLED");

	}
	
	@GetMapping("/pwChange")
	public void pwChange(Employee employee, Model model) {

		log.info("pwChange" );
		
	}
	
	@PostMapping("/pwChange")
	public String pwChangePOST(Employee employee, RedirectAttributes rttr) {

		log.info("pwChg Post");
		
		  repo.findById(employee.getUsername()).ifPresent(origin->{
			  origin.setPassword(pe.encode(employee.getPassword()));
			  origin.setPwFlag("1");
			  repo.save(origin);
		  });
			  
		return "redirect:/login";
	}
	
}
