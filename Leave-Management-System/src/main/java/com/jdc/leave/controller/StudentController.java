package com.jdc.leave.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.leave.model.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService service;
	
	@GetMapping
	public String index (@RequestParam Optional<String> name,
						@RequestParam Optional<String> phone,
						@RequestParam Optional<String> email,
						ModelMap model) {
		var list = service.search(name, phone, email);
		model.put("list", list);
		return "students";
	}

}
