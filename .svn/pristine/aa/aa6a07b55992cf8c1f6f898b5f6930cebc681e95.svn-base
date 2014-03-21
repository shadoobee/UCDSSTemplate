package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.RosterReport;

public class RosterLearnerRowMapper implements RowMapper<RosterReport> {

	@Override
	public RosterReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		RosterReport rr = new RosterReport();
		rr.setLearner(rs.getString("learner_name"));
		//rr.setPrimaryOrg(rs.getString("primary_org"));
		rr.setOrgCd(rs.getString("org_cd"));
		rr.setEmail(rs.getString("user_email"));
		rr.setPhone(rs.getString("phone"));
		//rr.setJob(rs.getString("job_title"));
		rr.setHomeDept(rs.getString("home_department"));
		rr.setEmployeeID(rs.getString("emp_cntry"));
		
		return rr;
	}

}
