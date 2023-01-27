package com.jdc.leave.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.leave.model.dto.input.ClassForm;
import com.jdc.leave.model.dto.input.RegistrationForm;
import com.jdc.leave.model.service.ClassService;
import com.jdc.leave.model.service.RegistrationService;
import com.jdc.leave.model.service.TeacherService;

@Controller
@RequestMapping("/classes")
public class ClassController {
	
	@Autowired
	private RegistrationService regService;
	@Autowired
	private ClassService clsService;
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping
	public String index (@RequestParam Optional<String> teacher,
						@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Optional<LocalDate> from,
						@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Optional<LocalDate> to,ModelMap model) {
		var result = clsService.search(teacher, from, to);
		model.put("list", result);
		model.put("teachers", teacherService.getAvailableTeachers());
		
		return "classes";
	}
	
	@GetMapping("edit")
	public String edit (@RequestParam Optional<Integer> id,ModelMap model) {
		model.put("teachers", teacherService.getAvailableTeachers());
		return "class-edit";
	}
	
	@PostMapping
	public String save (@Valid @ModelAttribute(name = "classForm") ClassForm form,BindingResult result) {
		
		if(result.hasErrors()) {
			return "class-edit";
		}
		//save form
		var id = clsService.save(form);
		
		//redirect to detaill view
		return "redirect:/classes/%d".formatted(id);
	}
	
	@GetMapping("{id}")
	public String showDetail (@PathVariable int id,ModelMap model) {
		var result = clsService.findDetailById(id);
		model.put("dto", result);
		return "class-detail";
	}
	
	@GetMapping("registration")
	public String editRegisteration (@RequestParam(required = false,defaultValue = "0")int classId,
									@RequestParam(required = false,defaultValue = "0")int studentId) {
		return "registration-edit";
	}
	
	@PostMapping("registration")
	public String saveRegistration (@Valid @ModelAttribute(name = "registForm") RegistrationForm form,BindingResult result) {
		
		if(result.hasErrors()) {
			return "registration-edit";
		}
		
		regService.save(form);
		
		return "redirect:/classes/registration/%d/%d".formatted(form.getClassId(),form.getStudentId());
	}
	
	@GetMapping("registration/{classId}/{studentId}")
	public String showRegistration (@PathVariable int classId,@PathVariable int studentId,ModelMap model) {
		var result = regService.findDetailById(classId,studentId);
		model.put("dto", result);
		return "registration-detail";
	}
	@ModelAttribute(name = "classForm")
	ClassForm classForm(Optional<Integer> id) {
		return id.filter(a -> a>0).map(clsService::findById).orElse(new ClassForm());
	}
	@ModelAttribute(name = "registForm")
	RegistrationForm registForm(
								@RequestParam(required = false,defaultValue = "0")int classId,
								@RequestParam(required = false,defaultValue = "0")int studentId,
								ModelMap model) {
		//edit form
		if(studentId>0) {
			regService.getFormById(classId,studentId);
		}
		
		//new form
		var form = new RegistrationForm();
		form.setClassId(classId);
		return form;
	}

}
