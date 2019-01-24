package com.naturals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.java.Log;

@Log
@Controller
public class LoginController {
	
	@GetMapping("/login")
	public void login() {
		log.info("login CALLED");
	}

}
