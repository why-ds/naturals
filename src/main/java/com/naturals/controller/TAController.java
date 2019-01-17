package com.naturals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naturals.domain.TimeAttendance;
import com.naturals.persistence.TimeAttendanceRepository;
import com.naturals.vo.PageMaker;
import com.naturals.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/ta/")
@Log
public class TAController {
	
	@Autowired
	TimeAttendanceRepository repo;
		
	@GetMapping("/list")
	public void view(@ModelAttribute("pageVO") PageVO vo, Model model){
		Pageable page = vo.makePageable(0, "tno");		
		Page<TimeAttendance> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword()), page);

		log.info("LIST CALLED" + page);
		log.info("LIST CALLED" + result);
		
		model.addAttribute("result", new PageMaker(result));
	}	
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo")TimeAttendance vo) {
		log.info("register get");
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo")TimeAttendance vo, RedirectAttributes rttr) {
		
		log.info("register post");
		
		repo.save(vo);
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/ta/list";
	}
	
}
