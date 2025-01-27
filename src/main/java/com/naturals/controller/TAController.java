package com.naturals.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naturals.domain.Department;
import com.naturals.domain.EAStatus;
import com.naturals.domain.ElectronicApproval;
import com.naturals.domain.Employee;
import com.naturals.domain.Position;
import com.naturals.domain.TAType;
import com.naturals.domain.TimeAttendance;
import com.naturals.persistence.DepartmentRepository;
import com.naturals.persistence.EAStatusRepository;
import com.naturals.persistence.ElectronicApprovalRepository;
import com.naturals.persistence.EmployeeRepository;
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

	@Autowired
	ElectronicApprovalRepository eaRepo;
	
	@Autowired
	EAStatusRepository easRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/list")
	public void list(@ModelAttribute("pageVO") PageVO vo, Model model, HttpServletRequest request) {
		
		String iFlag = null;
		
		if(request.getParameter("page")==null) {
			log.info("page == null");
			iFlag="0";
		}
		
		Pageable page = vo.makePageable(0, "taday");		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		log.info("empno :::::::::::: " + username);
		
		Page<TimeAttendance> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword(), vo.getSdate(), vo.getEdate(),username, iFlag), page);
		Iterable<TAType> taType = tarepo.findAll();
		
		Optional<Employee> employee = empRepo.findById(username);
		Optional<String> opPwFlag = employee.map(Employee::getPwFlag);
		String pwflag = 	opPwFlag.get();
		
		model.addAttribute("result", new PageMaker(result));		
		model.addAttribute("result2", taType);
		model.addAttribute("pwflag", pwflag);
	}
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo") TimeAttendance vo, ModelMap model) {

		Iterable<TAType> tAType = tarepo.findAll();
		Iterable<Department> department = deptRepo.findAll();
		Iterable<Position> position = posiRepo.findAll();

		model.addAttribute("result", tAType);
		model.addAttribute("result2", department);
		model.addAttribute("result3", position);

		log.info("register get");
	}

	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo") TimeAttendance vo, RedirectAttributes rttr) {

		log.info("register post");

		repo.save(vo);
		rttr.addFlashAttribute("msg", "regSuccess");

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

	@GetMapping("/modifyReq")
	public void modifyReq(Long tno, @ModelAttribute("pageVO") PageVO vo, Model model) {

		log.info("MODIFY TNO : " + tno);

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
	  
	  Iterable<TAType> tAType = tarepo.findAll(); Iterable<Department> department =
	  deptRepo.findAll(); Iterable<Position> position = posiRepo.findAll();
	  
	  model.addAttribute("result", tAType); model.addAttribute("result2", department); model.addAttribute("result3", position);
	  
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

		rttr.addFlashAttribute("msg", "delSuccess");

		return "redirect:/ta/list";
	}

	@PostMapping("/modifyReq")
	public String modifyReqPost(MultipartFile[] file, ElectronicApproval electronicApproval, PageVO vo, RedirectAttributes rttr) {
		
		log.info("MODIFY REQ :::: " + electronicApproval);
		electronicApproval.setEastatusno(Long.valueOf(1));
		log.info("getEastatusno : " + electronicApproval.getEastatusno());
		eaRepo.save(electronicApproval);
		log.info("save 끝");
		 rttr.addFlashAttribute("msg","regSuccess");
		 log.info(String.valueOf(rttr.getFlashAttributes())); 
		 
//		 repo.findById(electronicApproval.getTno()).ifPresent(origin -> {
//		 origin.setStarttime(timeAttendance.getStarttime());
//		 origin.setEndtime(timeAttendance.getEndtime());
//		 origin.setTatypeno(timeAttendance.getTatypeno());
//		 
//		 repo.save(origin); rttr.addFlashAttribute("msg", "modSuccess");
//		 rttr.addAttribute("tno", origin.getTno()); });

		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		 
//		 파일 추가 예정
//		String uploadFolder = "D:\\upload";
//
//		for (MultipartFile multipartFile : file) {
//			log.info("-----------------------------------");
//			log.info("Upload file name : " + multipartFile.getOriginalFilename());
//			log.info("Upload file size : " + multipartFile.getSize());
//			log.info("-----------------------------------");
//
//			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
//
//			try {
//				multipartFile.transferTo(saveFile);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		return "redirect:/ta/list";
	}

	/*
	 * public String registerPOST(@ModelAttribute("vo")TimeAttendance vo,	  RedirectAttributes rttr) {
	 * log.info("register post"); 
	 * repo.save(vo);
	 * rttr.addFlashAttribute("msg","regSuccess");
	 * log.info(String.valueOf(rttr.getFlashAttributes())); return
	 * "redirect:/ta/list"; }
	 */

	@PostMapping("/modify") 
	public String modifyPost(TimeAttendance timeAttendance, PageVO vo, RedirectAttributes rttr) {
	  log.info("MODIFY TA :::: " + timeAttendance);
	  repo.findById(timeAttendance.getTno()).ifPresent(origin->{
//	  origin.setStarttime(timeAttendance.getStarttime());
//	  origin.setEndtime(timeAttendance.getEndtime());
	  origin.setTatypeno(timeAttendance.getTatypeno());
	  
	  repo.save(origin); rttr.addFlashAttribute("msg", "modSuccess");
	  rttr.addAttribute("tno", origin.getTno()); });
	  
	  rttr.addAttribute("page", vo.getPage()); rttr.addAttribute("size",
	  vo.getSize()); rttr.addAttribute("type", vo.getType());
	  rttr.addAttribute("keyword", vo.getKeyword());
	  
	  return "redirect:/ta/view"; 
	 }


	@GetMapping("/calendar")
	public void calendar() {
		log.info("calendar호출");
	}

	@GetMapping("/sendEmail")
	public void sendMail(Long etno, String emailContent, RedirectAttributes rttr) {

		log.info(etno + "========TNO TEST");
		log.info(emailContent + "==========emailContent TEST");

		String positon = repo.findById(etno).get().getPosition().getPositionnm();
		String name = repo.findById(etno).get().getEmployee().getEmpnm();
		String tatype = repo.findById(etno).get().getTatype().getTatypenm();

		SimpleMailMessage msg = new SimpleMailMessage();

//		msg.setFrom("test@mail.com"); 
//		누구한테 보낼지 결정해야함
		msg.setTo("dsyoon@naturalsolution.co.kr");
		msg.setSubject(name + " " + positon + " " + tatype + " 관련 문의");
		msg.setText(emailContent);
		this.sender.send(msg); 
	}
	
	
	@GetMapping("/reqList")
	public void reqList(@ModelAttribute("pageVO") PageVO vo, Model model, HttpServletRequest request) {
		log.info("reqList Called");

		String iFlag = null;
		
		if(request.getParameter("page")==null) {
			iFlag="0";
		}
		
		Pageable page = vo.makePageable(0, "eano");

		String username = SecurityContextHolder.getContext().getAuthentication().getName();		
//		request.isUserInRole("ROLE_ADMIN")
		
		Page<ElectronicApproval> result = eaRepo.findAll(eaRepo.makePredicate2(vo.getType(), vo.getKeyword(), username, vo.getSdate(), vo.getEdate(), iFlag), page);
				
		Iterable<EAStatus> eaStatus = easRepo.findAll();

		model.addAttribute("result", new PageMaker(result));
		
		model.addAttribute("result2", eaStatus);
	}

}
