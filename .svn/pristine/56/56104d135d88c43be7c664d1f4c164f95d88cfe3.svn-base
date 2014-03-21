package edu.ucdavis.ss.lmsreports.data;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import edu.ucdavis.ss.lmsreports.domain.Status;
import edu.ucdavis.ss.lmsreports.rowmapper.StatusRowMapper;

@Repository("StatusDao")
@Transactional
public class StatusDaoImpl  implements StatusDao {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}




	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}




	public List<Status> getAll(){
		
		StringBuilder sql= new StringBuilder();
		sql.append(" SELECT * FROM STATUS ");
		return jdbcTemplate.query(sql.toString(), new StatusRowMapper());
		
	}

}
