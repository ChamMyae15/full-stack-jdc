package com.jdc.project.model.service.utils;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jdc.project.model.ProjectDbException;
import com.jdc.project.model.dto.Member;
import com.jdc.project.model.dto.Project;

@Component
public class ProjectHelper {
	
	
	@Value("${project.empty.name}")
	private String noName;
	@Value("${project.empty.manager}")
	private String noManager;
	@Value("${project.empty.start}")
	private String noStartDate;	
	
	

		
	public void validate(Project dto) {
		
		if(!StringUtils.hasLength(dto.getName())) {
			throw new ProjectDbException(noName);
		}
		if(dto.getManagerId()==0){
			throw new ProjectDbException(noManager);
		}
		if(dto.getStartDate()==null){
			throw new ProjectDbException(noStartDate);
		}
		
	}

	public Map<String, Object> insertParams(Project dto) {
		var params = new HashMap<String,Object>();
		
		params.put("name", dto.getName());
		params.put("description", dto.getDescription());
		params.put("manager", dto.getManagerId());
		params.put("start", Date.valueOf(dto.getStartDate()));
		params.put("months", dto.getMonths());
		return params;
	}
}
