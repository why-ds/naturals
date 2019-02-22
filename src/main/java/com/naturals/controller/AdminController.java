package com.naturals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naturals.domain.ElectronicApproval;
import com.naturals.persistence.ElectronicApprovalRepository;
import com.naturals.vo.PageMaker;
import com.naturals.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/admin/")
@Log
public class AdminController {

	@Autowired
	ElectronicApprovalRepository eaRepo;
	
	@GetMapping("/chkReq")
	public void chkReq(@ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("chkReq Called");
		Pageable page = vo.makePageable(0, "tno");

//		Page<TimeAttendance> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword()), page);

		String empno = SecurityContextHolder.getContext().getAuthentication().getName();

		log.info(empno + "===============empno");

		Page<ElectronicApproval> result = eaRepo.findAll(eaRepo.makePredicate(vo.getType(), vo.getKeyword(), empno), page);

		model.addAttribute("result", new PageMaker(result));
	}
}
