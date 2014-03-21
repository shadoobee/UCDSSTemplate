package edu.ucdavis.ss.lmsreports.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.ucdavis.ss.lmsreports.domain.Reports;

import edu.ucdavis.ss.lmsreports.rowmapper.ReportRowMapper;

public class ReportListDaoImpl implements ReportListDao {
	

	@Autowired
	private JdbcTemplate jdbcTemplate;



	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public List<Reports> getAll() {
	
		StringBuilder sql= new StringBuilder();
		sql.append(" SELECT * FROM REPORTS ORDER BY REPORT_NAME");
		return jdbcTemplate.query(sql.toString(), new ReportRowMapper());
	}

	

	
	

	

}
