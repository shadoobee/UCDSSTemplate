package edu.ucdavis.ss.lmsreports.data;

import java.util.List;


import javax.sql.DataSource;

import edu.ucdavis.ss.lmsreports.domain.Activity;
import edu.ucdavis.ss.lmsreports.domain.Learner;
import edu.ucdavis.ss.lmsreports.domain.LearningActivityCompletionReport;
import edu.ucdavis.ss.lmsreports.domain.RosterReport;
import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;

public interface ReportsDao {
	
	
	public List< Learner >getReportData(String domain, String activityId, String userCd, String orgCd );
	public List<Learner> getComplianceByLearnerReportData(String domain,String activityType, String activity, String statusType,String attended,String fromDate, String toDate, String orgCd, String userCd, String activityCodes);
	public List<RosterReport> getRosterActivityReportData(String activityId);
	public List<RosterReport> getRosterReportData(String activtyId);
	public List<RosterReport> getRelatedSessionData(String activityId);
	public List< Learner >getLearningActivityDetails( String activityId , String domainId, String userCd, String orgCd);
	public List <Activity> getUpcomingActivityReportData(ParameterPageUi formBean);
	public List <Learner> getUpcomingActivityDetails(String activityId, String userCd, String orgCd);

	public List<LearningActivityCompletionReport> getLearningActivityReportData(String activityId, String fromDate, String toDate, String domain,String userCd, String orgCd);

	public List<Activity> getLearningActivityCompletionReportData(String activities, String fromDate, String toDate, String domain,	String userCode, String orgCd);
	public List<Activity> getLearningActivityStatusData(ParameterPageUi formBean);
	
}
