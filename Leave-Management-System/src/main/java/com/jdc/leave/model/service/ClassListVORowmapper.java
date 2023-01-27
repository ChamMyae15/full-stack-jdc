package com.jdc.leave.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jdc.leave.model.dto.output.ClassListVO;

public class ClassListVORowmapper implements RowMapper<ClassListVO>{

	@Override
	public ClassListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		var vo = new ClassListVO();
		vo.setDescription(rs.getString("description"));
		vo.setId(rs.getInt("id"));
		vo.setMonths(rs.getInt("months"));
		vo.setStartDate(rs.getDate("startDate").toLocalDate());
		vo.setStudentCount(rs.getLong("studentCount"));
		vo.setTeacherId(rs.getInt("teacherId"));
		vo.setTeacherName(rs.getString("teacherName"));
		vo.setTeacherphone(rs.getString("teacherPhone"));
		return vo;
	}

}
