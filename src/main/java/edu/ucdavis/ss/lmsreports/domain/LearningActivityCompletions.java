package edu.ucdavis.ss.lmsreports.domain;

import java.util.Date;

public class LearningActivityCompletions {
	private String activityName;
	private String activityId;
	private String primaryDomain;
	private String activityCd;
	private String activityType;
	private Date startDt;
	private Date endDt;
	private String category;
	private int registrationCount;
	private int attendedCount;
	private int completedCount;
	private int completedCountSingleHeadcount;
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getPrimaryDomain() {
		return primaryDomain;
	}
	public void setPrimaryDomain(String primaryDomain) {
		this.primaryDomain = primaryDomain;
	}
	public String getActivityCd() {
		return activityCd;
	}
	public void setActivityCd(String activityCd) {
		this.activityCd = activityCd;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Date getStartDt() {
		return startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getRegistrationCount() {
		return registrationCount;
	}
	public void setRegistrationCount(int registrationCount) {
		this.registrationCount = registrationCount;
	}
	public int getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(int attendedCount) {
		this.attendedCount = attendedCount;
	}
	public int getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(int completedCount) {
		this.completedCount = completedCount;
	}
	public int getCompletedCountSingleHeadcount() {
		return completedCountSingleHeadcount;
	}
	public void setCompletedCountSingleHeadcount(int completedCountSingleHeadcount) {
		this.completedCountSingleHeadcount = completedCountSingleHeadcount;
	}
	
	
	

}
