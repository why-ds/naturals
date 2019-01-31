package com.naturals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naturals.domain.Department;
import com.naturals.domain.Position;
import com.naturals.domain.TAType;
import com.naturals.domain.TimeAttendance;
import com.naturals.persistence.DepartmentRepository;
import com.naturals.persistence.PositionRepository;
import com.naturals.persistence.TATypeRepository;
import com.naturals.persistence.TimeAttendanceRepository;
import com.naturals.vo.PageMaker;
import com.naturals.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/ta/")
@Log
public class TAController {

	@Autowired 
	MailSender sender;
	
	
	@Autowired
	TimeAttendanceRepository repo;

	@Autowired
	TATypeRepository tarepo;

	@Autowired
	DepartmentRepository deptRepo;

	@Autowired
	PositionRepository posiRepo;
	
	@GetMapping("/list")
	public void list(@ModelAttribute("pageVO") PageVO vo, Model model){
		Pageable page = vo.makePageable(0, "tno");
		
//		Page<TimeAttendance> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword()), page);
		
		String empno = SecurityContextHolder.getContext().getAuthentication().getName();
		
		log.info(empno+"===============empno");
		
		Page<TimeAttendance> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword(), empno), page);

		model.addAttribute("result", new PageMaker(result));
	}	
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo")TimeAttendance vo, ModelMap model) {
		
		Iterable<TAType> tAType = tarepo.findAll();
		Iterable<Department> department = deptRepo.findAll();
		Iterable<Position> position = posiRepo.findAll();

		model.addAttribute("result", tAType);
		model.addAttribute("result2", department);
		model.addAttribute("result3", position);
		
		log.info("register get");
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo")TimeAttendance vo, RedirectAttributes rttr) {
		
		log.info("register post");
		
		repo.save(vo);
		rttr.addFlashAttribute("msg","regSuccess");
		
		log.info(String.valueOf(rttr.getFlashAttributes()));
		
		return "redirect:/ta/list";
	}
	

	@GetMapping("/view")
	public void view(Long tno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		
		log.info("TNO :::::: " + tno);
		
		Iterable<TAType> tAType = tarepo.findAll();
		Iterable<Department> department = deptRepo.findAll();
		Iterable<Position> position = posiRepo.findAll();

		model.addAttribute("result", tAType);
		model.addAttribute("result2", department);
		model.addAttribute("result3", position);
		
		repo.findById(tno).ifPresent(timeAttendance -> model.addAttribute("vo", timeAttendance));
	}

	@GetMapping("/modify")
	public void modify(Long tno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		
		log.info("MODIFY TNO : " + tno);
		
		Iterable<TAType> tAType = tarepo.findAll();
		Iterable<Department> department = deptRepo.findAll();
		Iterable<Position> position = posiRepo.findAll();

		model.addAttribute("result", tAType);
		model.addAttribute("result2", department);
		model.addAttribute("result3", position);
		
		
		repo.findById(tno).ifPresent(timeAttendance -> model.addAttribute("vo", timeAttendance));
		
	}

	@PostMapping("/delete")
	public String delete(Long tno, PageVO vo, RedirectAttributes rttr) {
		
		log.info("DELETE  TNO : " + tno);
		
		repo.deleteById(tno);
		
		rttr.addFlashAttribute("msg", "success");
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		rttr.addFlashAttribute("msg","delSuccess");
		
		return "redirect:/ta/list";
	}
	
	@PostMapping("modify")
	public String modifyPost(TimeAttendance timeAttendance, PageVO vo, RedirectAttributes rttr) {
		
		log.info("MODIFY TA :::: " + timeAttendance);
		
		repo.findById(timeAttendance.getTno()).ifPresent(origin->{
			origin.setStarttime(timeAttendance.getStarttime());
			origin.setEndtime(timeAttendance.getEndtime());
			origin.setTatypeno(timeAttendance.getTatypeno());
			
			repo.save(origin);
			rttr.addFlashAttribute("msg", "modSuccess");
			rttr.addAttribute("tno", origin.getTno());
		});
		
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		
		return "redirect:/ta/view";
	}

	
	@GetMapping("/calendar")
	public void calendar() {
		log.info("calendar호출");
	}

	@GetMapping("/sendEmail")
	public void sendMail(Long tno, RedirectAttributes rttr) { 
		
		log.info(tno+"========TNO TEST");
		
		String positon = repo.findById(tno).get().getPosition().getPositionnm();
		String name = repo.findById(tno).get().getEmployee().getEmpnm();
		String tatype = repo.findById(tno).get().getTatype().getTatypenm();
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
//		msg.setFrom("test@mail.com"); 
//		누구한테 보낼지 결정해야함
		msg.setTo("dsyoon@naturalsolution.co.kr"); 
		msg.setSubject(name+" "+positon+" "+tatype+" 관련 문의"); 
		msg.setText("TEST 메일입니다. 죄송합니다."); 
		this.sender.send(msg);
	}	
	
}
