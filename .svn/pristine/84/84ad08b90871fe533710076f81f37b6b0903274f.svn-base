package edu.ucdavis.ss.lmsreports.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("ActivityTypeDao")
public class PingDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public String pingDB(){
		StringBuilder sql= new StringBuilder();
		sql.append(" SELECT GETDATE()");
		return (String) jdbcTemplate.queryForObject(sql.toString(), String.class);
	}
}
