package edu.ucdavis.ss.lmsreports.domain;

import java.util.Date;

public class ComplianceReport {
	String learner;
	String homeDepartment;
	String activityName;
	Date dueDate;
	String reqStatus;
	Date certAwardDt;
	String email;
	String orgName;
	public String getLearner() {
		return learner;
	}
	public void setLearner(String learner) {
		this.learner = learner;
	}
	public String getHomeDepartment() {
		return homeDepartment;
	}
	public void setHomeDepartment(String homeDepartment) {
		this.homeDepartment = homeDepartment;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public Date getCertAwardDt() {
		return certAwardDt;
	}
	public void setCertAwardDt(Date certAwardDt) {
		this.certAwardDt = certAwardDt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	

}
