package com.jdc.leave.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.leave.model.dto.input.LeaveForm;
import com.jdc.leave.model.dto.output.StudentDetailVo;
import com.jdc.leave.model.service.ClassService;
import com.jdc.leave.model.service.LeaveService;
import com.jdc.leave.model.service.StudentService;

@Controller
@RequestMapping("/leaves")
public class LeaveController {
	
	@Autowired
	private LeaveService service;
	@Autowired
	private StudentService stuService;
	@Autowired
	private ClassService clsService;
	
	@GetMapping
	public String index(
						
						@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Optional<LocalDate> from,
						@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Optional<LocalDate> to,ModelMap model) {
		var list = service.search(Optional.empty(), from, to);
		model.put("list", list);
		return "leaves";
	}
	@GetMapping("edit")
	public String edit(@RequestParam int classesId,@RequestParam int studentId,ModelMap model) {
		model.put("classInfo",clsService.findInfoById(classesId));
		model.put("studentInfo", stuService.findInfoById(studentId));
		return "leave-edit";
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute LeaveForm form,BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			model.put("classInfo",clsService.findInfoById(form.getClassId()));
			model.put("studentInfo", stuService.findInfoById(form.getStudentId()));
			return "leave-edit";
		}
		service.save(form);
		return "redirect:/leaves";
		
	}
	
	@ModelAttribute(name = "form")
	LeaveForm form(@RequestParam(required = false) Integer classesId,@RequestParam(required = false) Integer studentId) {
		
		if(null != classesId && null != studentId) {
		var form = new LeaveForm(classesId, studentId);
		form.setApplyDate(LocalDate.now());
		return form;
		}
		return new LeaveForm();
	}

}
