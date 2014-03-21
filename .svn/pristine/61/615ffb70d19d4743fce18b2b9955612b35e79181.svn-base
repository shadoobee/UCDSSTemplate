package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.RosterReport;

public class RosterSessionRowMapper implements RowMapper<RosterReport> {

	@Override
	public RosterReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		RosterReport rr = new RosterReport();
		rr.setSessionFromDate(rs.getString("startdt"));
		rr.setSessionToDate(rs.getString("enddt"));
		return rr;
	}

}
