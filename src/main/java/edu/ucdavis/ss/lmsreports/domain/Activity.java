package edu.ucdavis.ss.lmsreports.domain;

import java.util.Date;

public class Activity {
	private String activityId;
	private String activityName;
	private String activityCd;
	private Date startDt;
	private Date endDt;
	private String orgCd;
	private String category;
	private String primaryDomain;
	private String activityType;
	private int registeredCount;
	private int attendedCount;
	private int completedCount;
	private int completedSingleHeadcount;
	private int cancelledCount;
	private int waitListCount;
	private int noShowCount;
	private int expressedInterestCount;
	private int minCapacity;
	private int maxCapacity;
	private String status;
	
	

	public int getWaitListCount() {
		return waitListCount;
	}
	public void setWaitListCount(int waitListCount) {
		this.waitListCount = waitListCount;
	}
	public int getNoShowCount() {
		return noShowCount;
	}
	public void setNoShowCount(int noShowCount) {
		this.noShowCount = noShowCount;
	}
	public int getExpressedInterestCount() {
		return expressedInterestCount;
	}
	public void setExpressedInterestCount(int expressedInterestCount) {
		this.expressedInterestCount = expressedInterestCount;
	}
	public int getMinCapacity() {
		return minCapacity;
	}
	public void setMinCapacity(int minCapacity) {
		this.minCapacity = minCapacity;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCancelledCount() {
		return cancelledCount;
	}
	public void setCancelledCount(int cancelledCount) {
		this.cancelledCount = cancelledCount;
	}
	public String getPrimaryDomain() {
		return primaryDomain;
	}
	public void setPrimaryDomain(String primaryDomain) {
		this.primaryDomain = primaryDomain;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public int getRegisteredCount() {
		return registeredCount;
	}
	public void setRegisteredCount(int registeredCount) {
		this.registeredCount = registeredCount;
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
	public int getCompletedSingleHeadcount() {
		return completedSingleHeadcount;
	}
	public void setCompletedSingleHeadcount(int completedSingleHeadcount) {
		this.completedSingleHeadcount = completedSingleHeadcount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getActivityCd() {
		return activityCd;
	}
	public void setActivityCd(String activityCd) {
		this.activityCd = activityCd;
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
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	

}
