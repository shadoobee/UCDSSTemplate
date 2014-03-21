package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import edu.ucdavis.ss.lmsreports.domain.Learner;


public class LearningActivityDetailsRowMapper implements RowMapper<Learner> {

	@Override
	public Learner mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Learner l = new Learner();
		l.setLearner(rs.getString("learner"));
		l.setLearnerStatus(rs.getString("status"));
		l.setLearnerPrimaryDomain(rs.getString("org_name"));
		l.setLearnerPrimaryOrg(rs.getString("homedepartment"));
		l.setManager(rs.getString("manager"));
		l.setLearnerEmail(rs.getString("learner_email"));
		l.setStrCompletionDate(rs.getString("enddt"));
		l.setLearnerOrgCd(rs.getString("learner_org_cd"));
		l.setLearnerNumber(rs.getString("learner_usernumber"));
		l.setLearnerEmployeeNumber(rs.getString("learner_employee_number"));
		l.setLearnerJobTitle(rs.getString("job_name"));
		l.setActivity(rs.getString("activityName"));
		l.setCompletionStatus(rs.getString("completion_status"));
		l.setUserCd(rs.getString("user_cd"));
		
		return l;
	}

}
