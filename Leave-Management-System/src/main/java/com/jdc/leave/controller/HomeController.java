package com.jdc.leave.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.leave.model.dto.output.StudentDetailVo;
import com.jdc.leave.model.dto.output.StudentListVO;
import com.jdc.leave.model.service.LeaveService;
import com.jdc.leave.model.service.StudentService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired 
	private LeaveService service;
	@Autowired
	private StudentService stuService;
	
	@GetMapping
	public String index(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Optional<LocalDate> targetDate,ModelMap model) {
		
		Function<String, Boolean> hasAuthority = authority -> 
			SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a-> a.getAuthority().equals(authority));
		
		//Admin or Teacher
		if(hasAuthority.apply("Admin") || hasAuthority.apply("Teacher")) {
			model.put("targetDate", targetDate.orElse(LocalDate.now()));
			model.put("list", service.searchSummary(targetDate));
			return "teacher-home";
		}
		
		StudentDetailVo student = stuService.findStudentByLoginId(
									SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("dto", student);
		return "student-home";
	}

}
