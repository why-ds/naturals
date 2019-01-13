package com.naturals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/ta/")
@Log
public class TAController {
	
	@GetMapping("/view")
	public void view() {
		log.info("VIEW TEST");
	}
}
