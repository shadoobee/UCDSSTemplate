package edu.ucdavis.ss.lmsreports.ui;

import java.util.ArrayList;
import java.util.List;

import edu.ucdavis.ss.lmsreports.domain.Activity;
import edu.ucdavis.ss.lmsreports.domain.ActivityType;
import edu.ucdavis.ss.lmsreports.domain.Domain;
import edu.ucdavis.ss.lmsreports.domain.Location;
import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.domain.Status;

public class ParameterPageUi {
	private String selectedCampus;
	private List<ActivityType> activityTypes;
	private String selectedDomain;
	private String orgCd;
	private String activityName;
	private String courseCode;
	private String fromDate;
	private String toDate;
	private List<Activity> activityList;
	private String reportFile;
	private String reportType;
	private String reportName;
	private String activityId;
	private List<String> activityType;
	private String userRole;
	private Location location;
	private List<Location> locations;
	private List<Domain> domains;
	private String organization;
	private String userCd;
	private List<Status>  statusType ;
	private List<String> selectedStatusType;
	private String activityTypeList;
	private Person person;
	private boolean allSelected;
	
	
	
	
	
	
	
	
	
	

	
	
	public boolean isAllSelected() {
		return allSelected;
	}
	public void setAllSelected(boolean allSelected) {
		this.allSelected = allSelected;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getActivityTypeList() {
		return activityTypeList;
	}
	public void setActivityTypeList(String activityTypeList) {
		this.activityTypeList = activityTypeList;
	}
	
	public List<String> getSelectedStatusType() {
		return selectedStatusType;
	}
	public void setSelectedStatusType(List<String> selectedStatusType) {
		this.selectedStatusType = selectedStatusType;
	}
	public List<Status> getStatusType() {
		return statusType;
	}
	public void setStatusType(List<Status> statusType) {
		this.statusType = statusType;
	}
	public String getSelectedCampus() {
		return selectedCampus;
	}
	public void setSelectedCampus(String selectedCampus) {
		this.selectedCampus = selectedCampus;
	}
	public List<ActivityType> getActivityTypes() {
		return activityTypes;
	}
	public void setActivityTypes(List<ActivityType> activityTypes) {
		this.activityTypes = activityTypes;
	}
	public String getSelectedDomain() {
		return selectedDomain;
	}
	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomain = selectedDomain;
	}

	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public List<Activity> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	public String getReportFile() {
		return reportFile;
	}
	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public List<String> getActivityType() {
		return activityType;
	}
	public void setActivityType(List<String> activityType) {
		this.activityType = activityType;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	public List<Domain> getDomains() {
		return domains;
	}
	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getUserCd() {
		return userCd;
	}
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}


}
