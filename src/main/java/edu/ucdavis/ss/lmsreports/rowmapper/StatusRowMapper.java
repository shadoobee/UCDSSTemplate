package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.Status;

public class StatusRowMapper implements RowMapper<Status> {

	@Override
	public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
		Status status = new Status();
		status.setStatusId(rs.getInt("status_Id"));
		status.setStatusDesc(rs.getString("status_desc"));
		status.setStatusCd(rs.getInt("lms_status_cd"));
		status.setReportId(rs.getInt("report_id"));
		
		return status;
	}

}
