package edu.ucdavis.ss.lmsreports.service;

import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import edu.ucdavis.ss.lmsreports.domain.Activity;
import edu.ucdavis.ss.lmsreports.domain.ActivityType;
import edu.ucdavis.ss.lmsreports.domain.Learner;
import edu.ucdavis.ss.lmsreports.domain.Domain;
import edu.ucdavis.ss.lmsreports.domain.Location;
import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.domain.Reports;
import edu.ucdavis.ss.lmsreports.domain.RosterReport;
import edu.ucdavis.ss.lmsreports.domain.Status;
import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;


public interface LMSReportService {

	//dropdowns
	public void getBasicDropdowns(ParameterPageUi formBean);
	
	
	//report data
	public List<Learner> getComplianceByLearnerReportData(ParameterPageUi formBean);
	public List<Learner> getComplianceByActivityReportData(ParameterPageUi formBean);
	public List<RosterReport> getRosterActivityData(ParameterPageUi formBean);
	public List<RosterReport> getRosterLearnerData(ParameterPageUi formBean);
	public List<RosterReport> getRosterSessionData(ParameterPageUi formBean);
	public List<Activity> getLearningActivityCompletionData(ParameterPageUi formBean);
	public List<Activity> getUpcomingActivitiesReport(ParameterPageUi  formBean);
	public List<Learner> getUpcomingActivityDetail(String activityId, String userCd, String orgCd);
	public List<Learner> getLearningActivityDetail(String activityId, String domainId, String userCd, String orgCd);
	public List<Activity> getLearningActivityStatusData(ParameterPageUi formBean);
	
	//create reports
	public void createComplianceReport(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, List<?> reportData) throws Exception;
	public void createComplianceByActivityReport(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, List<Learner> reportData) throws Exception;
	public void createRosterReport(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, List<RosterReport> activityReportData, List<RosterReport> learnerReportData , List<RosterReport> sessionData) throws Exception;
	public void createLearningActivityCompletionReport(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, List<Activity>reportData) throws Exception;
	public void createLearningActivityDetailReport(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, List<Learner>reportData) throws Exception;
	public void createUpcomingActivitiesReport(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, List<Activity>reportData) throws Exception;
	public void createUpcomingActivityDetailReport(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, List<Learner>reportData) throws Exception;
	public void createLearningActivityStatusReport(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, List<Activity>reportData) throws Exception;
	
	// lists for parameter pages
	public List<Reports> getReportList();
	public List<Domain> getDomainsByLocation(String location);
	public List<Status> getAllStatus();
	public List<Location> getAllLocations();
	public List<ActivityType> getAllActivityTypes();
	public List<Activity> getActivities(String fromDate, String toDate, String domain, String activityType, String orgCd, String actName, String courseCd, String limitedActivities);
	public List<Activity> getFutureActivities(String fromDate, String toDate, String domain, String activityType, String orgCd, String actName, String courseCd, String limitedActivities);
	public List<Activity> getRequiredActivities(String domain);
	
    public IRunAndRenderTask getBirtTask(HttpServletRequest request, HttpServletResponse response,ParameterPageUi formBean) throws Exception;
    public void createReport(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, IRunAndRenderTask task)throws Exception;
    public String initParamPage(HttpServletRequest request, ParameterPageUi formBean, Model model);
    public String changeCampus(HttpServletRequest request, ParameterPageUi formBean,  Person person);
	public String reloadPage(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, BindingResult result, Model model, Person person) throws Exception;

	public Domain getDomainByLMSKey(String lmsKey);
	
	

		

	
}
