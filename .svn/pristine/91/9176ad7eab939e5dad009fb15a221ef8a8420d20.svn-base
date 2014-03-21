package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.Reports;

public class ReportRowMapper implements RowMapper<Reports> {

	@Override
	public Reports mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reports reports = new Reports();
		reports.setReportId(rs.getInt("report_id"));
		reports.setReportName(rs.getString("report_name"));
		reports.setReportDesc(rs.getString("report_desc"));
		reports.setPage(rs.getString("page"));
		reports.setReportFile(rs.getString("report_file"));
		return reports;
	}
	
	
	

}
