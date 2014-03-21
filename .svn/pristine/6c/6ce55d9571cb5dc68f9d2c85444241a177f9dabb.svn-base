package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.RosterReport;

public class RosterActivityRowMapper implements RowMapper<RosterReport> {

	@Override
	public RosterReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		RosterReport rr = new RosterReport();
		rr.setTotalRegistered(rs.getInt("total_registered"));
		rr.setInstructor(rs.getString("instructor_name"));
		rr.setActivityCd(rs.getString("code"));
		rr.setActivityName(rs.getString("ActivityName"));
		rr.setSessionFromDate(rs.getString("startdt"));
		rr.setSessionToDate(rs.getString("enddt"));
		rr.setActivityDesc(rs.getString("activitydesc"));
		rr.setLocation(rs.getString("loc_name"));
		rr.setMaxCapacity(rs.getInt("max_cap"));
		rr.setFacilityName(rs.getString("fac_name"));
		rr.setFacilityAdd1(rs.getString("fac_add1"));
		rr.setFacilityAdd2(rs.getString("fac_add2"));
		rr.setFacilityCity(rs.getString("fac_city"));
		rr.setFacilityState(rs.getString("fac_state"));
		rr.setFacilityZip(rs.getString("fac_zip"));
		
		return rr;
	}

}
