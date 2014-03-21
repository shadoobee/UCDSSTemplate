package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import edu.ucdavis.ss.lmsreports.domain.Learner;

public class ComplianceReportRowMapper implements RowMapper<Learner> {

	@Override
	public Learner mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Learner learner = new Learner();
		
		learner.setLearner(rs.getString("learner"));
		learner.setLearnerPrimaryDomain(rs.getString("homedepartment"));
		learner.setActivity(rs.getString("activityName"));
		learner.setDueDate(rs.getDate("DueDate"));
		learner.setCertStatus(rs.getString("certstatus"));
		learner.setCompletionDate(rs.getDate("cert_award_dt"));
		learner.setLearnerEmail(rs.getString("emp_email"));
		learner.setLearnerPrimaryOrg(rs.getString("org_name"));
		learner.setExpirationDate(rs.getDate("expiration_date"));
		learner.setTrainingDueDate(rs.getDate("training_due_date"));
		learner.setUserCd(rs.getString("userCd"));
		learner.setLearnerEmployeeNumber(rs.getString("emp_cntry"));
		
		//complianceReport.setLearner(rs.getString("learner"));
		//complianceReport.setHomeDepartment(rs.getString("homedepartment"));
		//complianceReport.setActivityName(rs.getString("activityName"));
		//complianceReport.setDueDate(rs.getDate("DueDate"));
		//complianceReport.setReqStatus(rs.getString("certstatus"));
		//complianceReport.setCertAwardDt(rs.getDate("cert_award_dt"));
		//complianceReport.setEmail(rs.getString("emp_email"));
		//complianceReport.setOrgName(rs.getString("org_name"));
		
		
		 
		 
		return learner;
	}
	

}
