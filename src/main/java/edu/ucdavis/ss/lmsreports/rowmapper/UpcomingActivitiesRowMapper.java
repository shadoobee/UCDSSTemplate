package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import edu.ucdavis.ss.lmsreports.domain.Activity;


public class UpcomingActivitiesRowMapper implements RowMapper<Activity> {

	@Override
	public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Activity r = new Activity();
		 r.setActivityName(rs.getString("ActivityName"));
		 r.setActivityCd(rs.getString("Code"));
		 r.setActivityType(rs.getString("ActLabel_Name"));
		 r.setPrimaryDomain(rs.getString("Org_name"));
		 r.setStartDt(rs.getTimestamp("StartDt"));
		 r.setEndDt(rs.getTimestamp("EndDt"));
		 r.setActivityId(rs.getString("Activity_PK"));
		 r.setRegisteredCount(rs.getInt("registered_count"));
		 r.setCancelledCount(rs.getInt("cancelled_count"));
		 return r;
	}


	

}
