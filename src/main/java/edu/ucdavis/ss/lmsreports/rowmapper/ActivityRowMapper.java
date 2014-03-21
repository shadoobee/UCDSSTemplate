package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.Activity;

public class ActivityRowMapper implements RowMapper<Activity> {

	@Override
	public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
		Activity activity = new Activity();
		activity.setActivityId(rs.getString("activity_pk"));
		activity.setActivityName(rs.getString("ActivityName"));
		activity.setStartDt(rs.getTimestamp("startdt"));
		activity.setEndDt(rs.getTimestamp("enddt"));
		activity.setActivityCd(rs.getString("code"));
		activity.setOrgCd(rs.getString("org_cd"));
		activity.setCategory(rs.getString("ActLabel_Name"));
		return activity;
	}

}
