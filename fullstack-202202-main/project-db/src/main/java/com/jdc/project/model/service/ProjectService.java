package com.jdc.project.model.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.project.model.dto.Project;
import com.jdc.project.model.dto.ProjectVO;
import com.jdc.project.model.service.utils.ProjectHelper;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectHelper projectHelper;
	
	@Autowired
	private SimpleJdbcInsert projectInsert;
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private RowMapper<Project> rowMapper;
	
	public ProjectService() {
		rowMapper = new BeanPropertyRowMapper<>(Project.class);
	}
	
	public int create(Project project) {
		// TODO Clear all test for create method
		projectHelper.validate(project);
		return projectInsert.executeAndReturnKey(projectHelper.insertParams(project)).intValue();
	}

	public Project findById(int id) {
		var sql = "select p.id,p.name,p.description,p.start startDate,p.months,m.id managerId,m.name managerName,m.login_id ManagerLogin,m.password,m.role,m.active from project p inner join member m on p.manager=m.id where p.id=:id";
		return template.queryForObject(sql, Map.of("id",id), rowMapper);
		
	}

	public List<ProjectVO> search(String projectName, String manager, LocalDate dateFrom, LocalDate dateTo) {
		var buffer = new StringBuffer("select p.id project,p.name projectName,p.description,p.start,p.months,m.id managerId,m.name managerName,m.login_id,m.password,m.role,m.active from project p inner join member m on p.manager=m.id where 1=1");
		var params = new HashMap<String,Object>();
		
		if(StringUtils.hasLength(projectName)) {
			buffer.append(" and lower(p.name) like :projectName");
			params.put("projectName", projectName.toLowerCase().concat("%"));
		}
		if(StringUtils.hasLength(manager)) {
			buffer.append(" and lower(m.name) like :manager");
			params.put("manager", manager.toLowerCase().concat("%"));
		}
		if(dateFrom!=null) {
			buffer.append(" and start>=:dateFrom");
			params.put("dateFrom", dateFrom);
		}
		if(dateTo!=null) {
			buffer.append(" and start<=:dateTo");
			params.put("dateTo", dateTo);
		}
		return template.queryForStream(buffer.toString(), params, rowMapper).map(a->(ProjectVO)a).toList();
	}

	public int update(int id, String name, String description, LocalDate startDate, int month) {
		String OriginalName = template.queryForObject("select name from project where id=:id", Map.of("id",id), String.class);
		String OriginalDesp = template.queryForObject("select description from project where id=:id", Map.of("id",id), String.class);
		LocalDate OriginalStart = template.queryForObject("select start from project where id=:id", Map.of("id",id), LocalDate.class);
		Integer OriginalMonth = template.queryForObject("select months from project where id=:id", Map.of("id",id), Integer.class);
		
		StringBuffer buffer = new StringBuffer("update project set id=id");
		var params = new HashMap<String,Object>();
		
		if(name!=OriginalName) {
			buffer.append(" and name=:name");
			params.put("name", name);
		}
		if(description!=OriginalDesp) {
			buffer.append(" and description=:description");
			params.put("description", description);
			
		}
		if(startDate!=OriginalStart) {
			buffer.append(" and start=:startDate");
			params.put("startDate", startDate);
	
		}
		if(month!=OriginalMonth) {
			buffer.append(" and months=:month");
			params.put("month", month);
			
		}
		buffer.append(" where id=:id");
		params.put("id",id);
		return template.update(buffer.toString(), params);
		
	}

	public int deleteById(int id) {
		// TODO Clear all test for create method
		return template.update("delete from project where id=:id", Map.of("id",id));
	}

}
