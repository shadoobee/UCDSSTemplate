package edu.ucdavis.ss.lmsreports.data;

import java.util.List;



import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import edu.ucdavis.ss.lmsreports.domain.ActivityType;
import edu.ucdavis.ss.lmsreports.rowmapper.ActivityTypeRowMapper;

@Repository("ActivityTypeDao")
@Transactional
public class ActivityTypeDaoImpl implements ActivityTypeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	@Override
	public List<ActivityType> getAllActivities() {
		
		StringBuilder sql= new StringBuilder();
		sql.append(" SELECT actlabel_pk, actlabel_name FROM actlabel order by actlabel_name");
		return jdbcTemplate.query(sql.toString(), new ActivityTypeRowMapper());
	}
	


}
