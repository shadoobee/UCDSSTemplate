package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.Learner;

public class UpcomingActivitiesDetailsRowMapper implements RowMapper<Learner> {

	@Override
	public Learner mapRow(ResultSet rs, int rowNum) throws SQLException {
		Learner c = new Learner();
		c.setLearnerPrimaryDomain(rs.getString("primaryorganization"));
	    c.setActivityCode(rs.getString("code"));
	    c.setLearner(rs.getString("learner"));
		c.setLearnerEmail(rs.getString("learner_email"));
		c.setManager(rs.getString("manager"));
	    c.setActivity(rs.getString("ActivityName"));
	    c.setDueDate(rs.getDate("DueDate"));
	    c.setCompletionDate(rs.getDate("CompletionDate"));
	    c.setExpirationDate(rs.getDate("ExpirationDate"));
	   	c.setCertStatus(rs.getString("cert_status"));
		c.setRegistrationStatus(rs.getString("reg_status"));
		c.setLearnerStatus(rs.getString("status"));
		c.setLearnerOrgCd(rs.getString("learner_org_cd"));
		c.setLearnerPrimaryOrg(rs.getString("homedepartment"));
		c.setLearnerNumber(rs.getString("learner_usernumber"));
		c.setLearnerEmployeeNumber(rs.getString("learner_employee_number"));
		c.setLearnerJobTitle(rs.getString("job_name"));
		c.setUserCd(rs.getString("user_cd"));
		
		return c;
	}
	

}
