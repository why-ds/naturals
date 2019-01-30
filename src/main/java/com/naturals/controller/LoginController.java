package com.naturals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naturals.domain.TimeAttendance;
import com.naturals.persistence.EmployeeRepository;

import lombok.extern.java.Log;

@Log
@Controller
public class LoginController {

	@Autowired
	EmployeeRepository repo;

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
}
