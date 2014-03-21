package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.Activity;

public class LearningActivityRowMapper implements RowMapper<Activity> {

	@Override
	public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
         Activity l = new Activity();
		
		l.setActivityId(rs.getString("activity_pk"));
		l.setActivityName(rs.getString("ActivityName"));
		l.setActivityCd(rs.getString("code"));
		l.setActivityType(rs.getString("actlabel_name"));
		l.setCategory(rs.getString("ConType_Name"));
		l.setEndDt(rs.getTimestamp("enddt"));
		l.setStartDt(rs.getTimestamp("startdt"));
		l.setPrimaryDomain(rs.getString("org_name"));
		l.setRegisteredCount(rs.getInt("registered_count"));
		l.setAttendedCount(rs.getInt("attended_count"));
		l.setCompletedCount(rs.getInt("completed_count"));
		l.setCompletedSingleHeadcount(rs.getInt("completed_single_headcount"));
		
		return l;
	}

	
	
	

}
