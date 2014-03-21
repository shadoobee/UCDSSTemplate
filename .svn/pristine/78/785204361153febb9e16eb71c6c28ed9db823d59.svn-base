package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.ActivityType;

public class ActivityTypeRowMapper implements RowMapper<ActivityType> {

	@Override
	public ActivityType mapRow(ResultSet rs, int rowNum) throws SQLException {
		ActivityType activityType = new ActivityType();
		activityType.setActivityTypeDesc(rs.getString("actlabel_name"));
		activityType.setActivityTypeId(rs.getInt("actlabel_pk"));
		return activityType;
	}
	

}
