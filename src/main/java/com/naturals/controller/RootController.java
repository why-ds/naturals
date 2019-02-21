package com.naturals.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RootController {
	
	@RequestMapping("/")
	public String index(){
		return "/index";
	}
	
	@GetMapping("/sFileUpload")
	public String sFileUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException{

	      // Save file on system
	      if (!file.getOriginalFilename().isEmpty()) {
	         BufferedOutputStream outputStream = new BufferedOutputStream(
	               new FileOutputStream(
	                     new File("D:/SingleFileUpload", file.getOriginalFilename())));
	         outputStream.write(file.getBytes());
	         outputStream.flush();
	         outputStream.close();

	         model.addAttribute("msg", "File uploaded successfully.");
	      } else {
	         model.addAttribute("msg", "Please select a valid file..");
	      }

	      return "fileUploadForm";
	   }
}
