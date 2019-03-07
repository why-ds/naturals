package com.naturals.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naturals.domain.Department;
import com.naturals.domain.EAStatus;
import com.naturals.domain.ElectronicApproval;
import com.naturals.domain.Position;
import com.naturals.domain.TAType;
import com.naturals.persistence.DepartmentRepository;
import com.naturals.persistence.EAStatusRepository;
import com.naturals.persistence.ElectronicApprovalRepository;
import com.naturals.persistence.PositionRepository;
import com.naturals.persistence.TATypeRepository;
import com.naturals.persistence.TimeAttendanceRepository;
import com.naturals.vo.PageMaker;
import com.naturals.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/admin/")
@Log
public class AdminController {

	@Autowired
	ElectronicApprovalRepository eaRepo;

	@Autowired
	TimeAttendanceRepository taRepo;
	
	@Autowired
	TATypeRepository typeRepo;

	@Autowired
	DepartmentRepository deptRepo;

	@Autowired
	PositionRepository posiRepo;
	
	@Autowired
	EAStatusRepository easRepo;
	
	@GetMapping("/chkReqList")
	public void chkReqList(@ModelAttribute("pageVO") PageVO vo, @ModelAttribute("eavo") ElectronicApproval eavo, Model model, HttpServletRequest request) {

		String iFlag = null;
		
		if(request.getParameter("page")==null) {
			iFlag="0";
		}
//		request.isUserInRole("ROLE_ADMIN")
		Pageable page = vo.makePageable(0, "eano");		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();		
		Page<ElectronicApproval> result = eaRepo.findAll(eaRepo.makePredicate(vo.getType(), vo.getKeyword(), username, vo.getSdate(), vo.getEdate(),  iFlag), page);		
		Iterable<EAStatus> eaStatus = easRepo.findAll();
		
		model.addAttribute("result", new PageMaker(result));		
		model.addAttribute("result2", eaStatus);
	}

	
	@GetMapping("/chkReqView")
	public void chkReqView(Long eano, @ModelAttribute("pageVO") PageVO vo, Model model) {

		log.info("eano :::::: " + eano);

		Iterable<TAType> taType = typeRepo.findAll();
		Iterable<Department> department = deptRepo.findAll();
		Iterable<Position> position = posiRepo.findAll();

		model.addAttribute("result", taType);
		model.addAttribute("result2", department);
		model.addAttribute("result3", position);

		eaRepo.findById(eano).ifPresent(electronicApproval -> model.addAttribute("vo", electronicApproval));
	}

	@PostMapping("/chkReqApproval") 
	public String chkReqApproval(ElectronicApproval electronicApproval, PageVO vo, RedirectAttributes rttr) {
		
	  log.info("chkReqApproval :::: " + electronicApproval);
	  log.info("chkReqApproval.tno ::: " + electronicApproval.getTno());
	  
	  taRepo.findById(electronicApproval.getTno()).ifPresent(origin->{
		  origin.setStarttime(electronicApproval.getStarttime());
		  origin.setEndtime(electronicApproval.getEndtime());
		  origin.setTatypeno(electronicApproval.getTatypeno());
		  origin.setUpdateempno(SecurityContextHolder.getContext().getAuthentication().getName());
		  
		  taRepo.save(origin); 
	  });

	  eaRepo.findById(electronicApproval.getEano()).ifPresent(origin->{
		  origin.setEastatusno(electronicApproval.getEastatusno());
		  origin.setMemo(electronicApproval.getMemo());
		  origin.setUpdateempno(SecurityContextHolder.getContext().getAuthentication().getName());
		  
		  eaRepo.save(origin); 
		  
		  rttr.addFlashAttribute("msg", "approvalSuccess");
		  rttr.addAttribute("eano", origin.getEano()); 
	  });

	  rttr.addAttribute("page", vo.getPage()); rttr.addAttribute("size", vo.getSize()); rttr.addAttribute("type", vo.getType());
	  rttr.addAttribute("keyword", vo.getKeyword());
	  
	  return "redirect:/admin/chkReqList"; 
	 }	

	@PostMapping("/chkReqDelete")
	public String chkReqDelete(Long eano, PageVO vo, RedirectAttributes rttr) {

		log.info("DELETE  EANO : " + eano);

		eaRepo.deleteById(eano);

		rttr.addFlashAttribute("msg", "success");
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		rttr.addFlashAttribute("msg", "delSuccess");

		return "redirect:/admin/chkReqList";
	}

	@PostMapping("/chkReqReturn") 
	public String chkReqReturn(ElectronicApproval electronicApproval, PageVO vo, RedirectAttributes rttr) {
		
	  log.info("chkReqReturn :::: " + electronicApproval);
	  log.info("electronicApproval.getEastatusno()"+electronicApproval.getEastatusno());
	  log.info("getName()"+SecurityContextHolder.getContext().getAuthentication().getName());
	  
	  eaRepo.findById(electronicApproval.getEano()).ifPresent(origin->{
		  origin.setEastatusno(electronicApproval.getEastatusno());
		  origin.setMemo(electronicApproval.getMemo());
		  origin.setUpdateempno(SecurityContextHolder.getContext().getAuthentication().getName());
		  
		  eaRepo.save(origin); 
		  
		  rttr.addFlashAttribute("msg", "returnSuccess");
		  rttr.addAttribute("eano", origin.getEano()); 
	  });
	  
	  rttr.addAttribute("page", vo.getPage()); rttr.addAttribute("size", vo.getSize()); rttr.addAttribute("type", vo.getType());
	  rttr.addAttribute("keyword", vo.getKeyword());
	  
	  return "redirect:/admin/chkReqList"; 
	 }	
	
}
