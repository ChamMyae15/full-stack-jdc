package com.jdc.leave.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.Param;

import com.jdc.leave.model.dto.input.LeaveForm;
import com.jdc.leave.model.dto.output.LeaveListVO;
import com.jdc.leave.model.dto.output.LeaveSummaryVO;
@Service
public class LeaveService {
	
	private NamedParameterJdbcTemplate template;
	@Autowired
	private ClassService clsService;
	private BeanPropertyRowMapper<LeaveListVO> rowMapper;
	private SimpleJdbcInsert leaInsert;
	private SimpleJdbcInsert leaDayInsert;
	
	private static final String LEAVECOUNT_SQL ="""
			select count(leave_date) from leaves_day where leave_date = :leaDate and leaves_classes_id = :classId
			""";
	
	private static final String SELECT_SQL = """
			select sa.name student,s.phone studentPhone,ta.name teacher,l.apply_date applyDate,l.start_date startDate,l.days,l.reason 
			from classes c join leaves l on c.id = l.classes_id 
			join student s on l.student_id = s.id 
			join account sa on s.id = sa.id
			join teacher t on c.teacher_id = t.id 
			join account ta on t.id = ta.id join leaves_day ld on l.apply_date = ld.leaves_apply_date and l.classes_id = ld.leaves_classes_id and l.student_id = ld.leaves_student_id 
			where 1=1 
			""";
	
	private static final String GROUBY_SQL = """
			order by l.start_date,l.apply_date,sa.name
			""";
	
	public LeaveService(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		rowMapper = new BeanPropertyRowMapper<>(LeaveListVO.class);
		
		leaInsert = new SimpleJdbcInsert(dataSource);
		leaInsert.setTableName("leaves");
		
		leaDayInsert = new SimpleJdbcInsert(dataSource);
		leaDayInsert.setTableName("leaves_day");
	}
	
	public List<LeaveListVO> search(Optional<Integer> classId,Optional<LocalDate> from,Optional<LocalDate> to){
		
		StringBuffer buffer = new StringBuffer(SELECT_SQL);
		
		var params = new HashMap<String,Object>();
		
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.isAuthenticated() && authentication.getAuthorities().contains(authority("Student"))) {
			if(authentication instanceof UsernamePasswordAuthenticationToken token) {
				buffer.append("and sa.email = :email ");
				params.put("email", token.getName() );
			}
		}
		
		buffer.append(classId.filter(a-> a != null && a>0)
				.map(a-> {
					params.put("classId", a);
					return "and l.classes_id = :classId ";
				}).orElse(""));
		
		buffer.append(from.map(a -> {
			params.put("from", Date.valueOf(a));
			return "and ld.leave_date >= :from ";
		}).orElse("")
		);
		
		buffer.append(to.map(a -> {
			params.put("to", Date.valueOf(a));
			return "and ld.leave_date <= :to ";
		}).orElse("")
		);
		
		buffer.append(GROUBY_SQL);
		
		return template.query(buffer.toString(), params, rowMapper);
	}
	
	
	private GrantedAuthority authority(String string) {
		
		return AuthorityUtils.commaSeparatedStringToAuthorityList(string).get(0);
	}

	@Transactional
	public void save(LeaveForm form) {
		 leaInsert.execute(form.leavesInsertParam());
		 
		 for(var param : form.leavesDayInsertParam()) {
			 leaDayInsert.execute(param);
		 }
	}
	
	public List<LeaveSummaryVO> searchSummary(Optional<LocalDate> target){
		
		//find classes
		var classes = clsService.search(Optional.ofNullable(null), Optional.ofNullable(null), Optional.ofNullable(null));
		
		var result = classes.stream().map(LeaveSummaryVO::new).toList();
		
		for (var vo : result) {
			vo.setLeaves(findLeavesByClassId(vo.getClassId(),target.orElse(LocalDate.now())));
			
		}
		return result;
	}

	private long findLeavesByClassId(int classId, LocalDate date) {
		
		return template.queryForObject(LEAVECOUNT_SQL, Map.of("leaDate",Date.valueOf(date),"classId",classId),Long.class);
	}

}
