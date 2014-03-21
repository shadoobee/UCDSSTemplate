package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.Activity;

public class LearningActiivtyStatusRowMapper implements RowMapper<Activity> {

	public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Activity l = new Activity();
		 
		 l.setActivityId(rs.getString("Activity_PK"));
		 l.setActivityName(rs.getString("activityName"));
		 l.setActivityCd(rs.getString("code"));
		 l.setStartDt(rs.getTimestamp("startDt"));
		 l.setEndDt(rs.getTimestamp("endDt"));
		 l.setMaxCapacity(rs.getInt("maxCapacity"));
		 l.setStatus(rs.getString("activityStatus"));
		 l.setPrimaryDomain(rs.getString("primaryOrg"));
		 l.setActivityType(rs.getString("ActLabel_Name"));
		 l.setRegisteredCount(rs.getInt("registered_count"));
		 l.setWaitListCount(rs.getInt("waitlist_count"));
		 l.setCancelledCount(rs.getInt("cancelled_count"));
		 l.setNoShowCount(rs.getInt("noshow_count"));
		 l.setExpressedInterestCount(rs.getInt("expressed_interest_count"));
		 l.setAttendedCount(rs.getInt("attended_count"));
		 
		return l;
	}
 
}
