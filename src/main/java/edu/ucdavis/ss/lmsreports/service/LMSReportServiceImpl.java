package edu.ucdavis.ss.lmsreports.service;

import java.util.List;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import edu.ucdavis.ss.lmsreports.birtengine.BirtEngine;
import edu.ucdavis.ss.lmsreports.data.ActivityDao;
import edu.ucdavis.ss.lmsreports.data.ActivityTypeDao;
import edu.ucdavis.ss.lmsreports.data.DomainDao;
import edu.ucdavis.ss.lmsreports.data.LocationDao;
import edu.ucdavis.ss.lmsreports.data.PersonDao;
import edu.ucdavis.ss.lmsreports.data.PingDao;
import edu.ucdavis.ss.lmsreports.data.ReportListDao;
import edu.ucdavis.ss.lmsreports.data.ReportsDao;
import edu.ucdavis.ss.lmsreports.data.StatusDao;
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


@Service("LMSReportService")
public class LMSReportServiceImpl implements LMSReportService {
	private static final int MAX_SIZE = 1000;
	
	@Autowired
	private LocationDao locationDao;
	
    @Autowired
    private ActivityTypeDao activityTypeDao;
    
    @Autowired
    private DomainDao domainDao;
    
    @Autowired
    private StatusDao statusDao;
    
     
    @Autowired
   private PersonDao personDao;
    
    @Autowired
    private ReportListDao reportListDao;
    
    private static final String FILE_DELIM = System.getProperty("file.separator");
 
    
    @Autowired
    private ReportsDao reportsDao;
    
   
    
    @Autowired 
    private ActivityDao activityDao;
    
	private static Logger LOG = Logger.getLogger(LMSReportService.class); 

	@Override
	public List<Reports> getReportList() {
		 return reportListDao.getAll();
	}

	@Override
	public List<Learner> getComplianceByLearnerReportData(
			ParameterPageUi formBean) {
		String attended = null;
		StringBuilder requiredStatus = new StringBuilder();
		if( formBean.getSelectedStatusType()!= null){
		for(String str : formBean.getSelectedStatusType()){
			if(str.equalsIgnoreCase("-1")){
				attended = "1";
			} else {
				requiredStatus.append(str);
				requiredStatus.append(",");
			}
		}
		
		if (requiredStatus.length() > 0){
			requiredStatus.deleteCharAt(requiredStatus.length() -1 );
		}
		
		}
		
		StringBuilder activityCodes = new StringBuilder();
		List<String> activityList = personDao.getActivityListForPerson(Integer.toString(formBean.getPerson().getId() ));
		if(activityList != null &&  activityList.size() > 0){
			for(String  act: activityList){
				activityCodes.append("\'");
				activityCodes.append(act);
				activityCodes.append("\'");
				activityCodes.append(",");
				
			}
		}
		if (activityCodes.length() > 0){
			activityCodes.deleteCharAt(activityCodes.length() -1 );
		}
	
		return reportsDao.getComplianceByLearnerReportData(formBean.getSelectedDomain(), formBean.getActivityTypeList(), formBean.getActivityName(), requiredStatus.toString(), attended, formBean.getFromDate(), formBean.getToDate(), formBean.getOrgCd(), formBean.getUserCd(), activityCodes.toString());
	}

	@Override
	public List<Learner> getComplianceByActivityReportData(
			ParameterPageUi formBean) {
		return reportsDao.getReportData(formBean.getSelectedDomain(),  formBean.getActivityId(), formBean.getUserCd(), formBean.getOrgCd());
	}

	@Override
	public List<Domain> getDomainsByLocation(String location) {
		return domainDao.getFullyQualifiedDomainForLoaction(location);
	}

	@Override
	public Domain getDomainByLMSKey(String lmsKey) {
		return domainDao.getDomainByLMSKey(lmsKey);
	}

	@Override
	public List<Status> getAllStatus() {
		return statusDao.getAll();
	}

	




	@Override
	public List<ActivityType> getAllActivityTypes() {
		return activityTypeDao.getAllActivities();
	}

	@Override
	public List<Activity> getActivities(String fromDate, String toDate,
			String domain, String activityType, String orgCd, String actName, String courseCd, String limitedActivites) {
		return activityDao.getSelectedActivities(domain, fromDate, toDate, activityType , orgCd, actName, courseCd, limitedActivites);
	}
	@Override
	public List<Activity> getFutureActivities(String fromDate, String toDate,
			String domain, String activityType, String orgCd, String actName, String courseCd, String limitedActivities) {
		return activityDao.getSelectedFutureActivities(domain, fromDate, toDate, activityType , orgCd, actName, courseCd, limitedActivities);
	}
	
	

	@Override
	public List<Activity> getRequiredActivities(String domain) {
		return activityDao.getSelectedRequiredActivities( domain);
	}

	@Override
	public List<Location> getAllLocations() {
		return locationDao.getAll();
	}

	@Override
	public void createComplianceReport(HttpServletRequest request,
			HttpServletResponse response, ParameterPageUi formBean,
			List<?> reportData) throws Exception {
		
		 LOG.info("creating Compliance By Learner Report");	
		 IRunAndRenderTask task = getBirtTask(request, response, formBean) ;  
		 task.setParameterValue("resultSet", reportData); 
		 createReport(request, response, formBean, task);
				
				
			
	  
			
			
		}

	

	@Override
	public List<RosterReport> getRosterActivityData(ParameterPageUi formBean) {
		return reportsDao.getRosterActivityReportData(formBean.getActivityId());
	}

	@Override
	public List<RosterReport> getRosterLearnerData(ParameterPageUi formBean) {
		return reportsDao.getRosterReportData(formBean.getActivityId());
	}

	
	
	@Override
	public void createRosterReport(HttpServletRequest request,
			HttpServletResponse response, ParameterPageUi formBean,
			List<RosterReport> activityReportData, List<RosterReport> learnerReportData, List<RosterReport> sessionData) throws Exception {
		
		 LOG.info("creating roster report");	
		 IRunAndRenderTask task = getBirtTask(request, response, formBean);  
	     //pass the dataset to the birt report
		 task.setParameterValue("activityResultSet", activityReportData);
		 task.setParameterValue("learnerResultSet", learnerReportData);
         task.setParameterValue("sessionResultSet", sessionData);
		 createReport(request, response, formBean, task);
				
		
	}


	@Override
	public void createLearningActivityCompletionReport(
			HttpServletRequest request, HttpServletResponse response,
			ParameterPageUi formBean,
			List<Activity> reportData) throws Exception {
		
		 LOG.info("creating LearningActivityCompletion report");	
		 IRunAndRenderTask task = getBirtTask(request, response, formBean);
	     //pass the dataset to the birt report
		 task.setParameterValue("resultSet", reportData);
		 task.setParameterValue("reportType", formBean.getReportType());
		 task.setParameterValue("userCd", formBean.getUserCd());
		 task.setParameterValue("orgCd", formBean.getOrgCd());
		 task.setParameterValue("domain", formBean.getSelectedDomain());
				
		createReport(request, response, formBean, task);
		
	}

	@Override
	public List<Activity> getLearningActivityCompletionData(
			ParameterPageUi formBean) {
		//return reportsDao.getLearningActivityReportData(formBean.getActivityId(), formBean.getFromDate(), formBean.getToDate(), formBean.getSelectedDomain());
		return reportsDao.getLearningActivityCompletionReportData(formBean.getActivityId(), formBean.getFromDate(), formBean.getToDate(), formBean.getSelectedDomain(), formBean.getUserCd(), formBean.getOrgCd());
	}

	
	@Override
	public void createComplianceByActivityReport(HttpServletRequest request,
			HttpServletResponse response, ParameterPageUi formBean,
			List<Learner> reportData) throws Exception {
		
		 LOG.info("creating ComplianceByActivity report");	
		 IRunAndRenderTask task = getBirtTask(request, response, formBean);  
	     task.setParameterValue("resultSet", reportData);
		 createReport(request, response, formBean, task);
				
				
			
	  
			
		
	}

	@Override
	public List<RosterReport> getRosterSessionData(ParameterPageUi formBean) {
		
		return reportsDao.getRelatedSessionData(formBean.getActivityId());
	}

	@Override
	public List<Activity> getUpcomingActivitiesReport(ParameterPageUi formBean) {
		return  reportsDao.getUpcomingActivityReportData(formBean);
	}

	@Override
	public void createUpcomingActivitiesReport(HttpServletRequest request,
			HttpServletResponse response,
			ParameterPageUi formBean,
			List<Activity> reportData) throws Exception {
	
		     
		     IRunAndRenderTask task = getBirtTask(request, response, formBean);
	        	//pass the dataset to the birt report
				task.setParameterValue("resultSet", reportData);
				task.setParameterValue("reportType", formBean.getReportType());
				task.setParameterValue("userCd",formBean.getUserCd());
				task.setParameterValue("orgCd", formBean.getOrgCd());
				task.setParameterValue("domain", formBean.getSelectedDomain());
			   createReport(request, response, formBean, task);
		
	}

	@Override
	public List<Learner> getUpcomingActivityDetail(String activityId, String userCd, String orgCd) {
		// TODO Auto-generated method stub
		return reportsDao.getUpcomingActivityDetails(activityId, userCd, orgCd);
	}

	@Override
	public void createUpcomingActivityDetailReport(HttpServletRequest request,
			HttpServletResponse response,
			ParameterPageUi formBean,
			List<Learner> reportData) throws Exception {
	      LOG.info("creating Upcoming Activities report");	
	     IRunAndRenderTask task = getBirtTask(request, response, formBean);	    
	     task.setParameterValue("resultSet", reportData);
		 createReport(request, response, formBean, task);
	}

	
	@Override
	public List<Learner> getLearningActivityDetail( String activityId, String domainId, String userCd, String orgCd) {
		// TODO Auto-generated method stub
		return reportsDao.getLearningActivityDetails(activityId, domainId, userCd, orgCd);
	}

	@Override
	public void createLearningActivityDetailReport(HttpServletRequest request,
			HttpServletResponse response,
			ParameterPageUi formBean, List<Learner> reportData) throws Exception {
		      
		        LOG.info("creating Learning Activities Details report");	
		         IRunAndRenderTask task = getBirtTask(request, response, formBean);
				//pass the dataset to the birt report
				task.setParameterValue("resultSet", reportData);
				createReport(request, response, formBean, task);
			
		
	}

	public void createLearningActivityStatusReport(HttpServletRequest request,
			HttpServletResponse response, ParameterPageUi formBean,
			List<Activity> reportData) throws Exception {
		
		        LOG.info("creating Learning Activities Status report");	
		        IRunAndRenderTask task = getBirtTask(request, response, formBean);
				task.setParameterValue("resultSet", reportData);
			    createReport(request, response, formBean, task);
			
	}

	public List<Activity> getLearningActivityStatusData(
			ParameterPageUi formBean) {
		return reportsDao.getLearningActivityStatusData(formBean);
	}
	
	

	@Override
	public void getBasicDropdowns(ParameterPageUi formBean) {
		String location = null;
		String loc = null;
	    List<Location> locationList = null;
		if(formBean.getUserRole().equalsIgnoreCase("Admin")|| formBean.getUserRole().equalsIgnoreCase("SuperUser")){
			locationList = getAllLocations();
						
		} 
		
		if (formBean.getSelectedCampus() != null){
			 location = formBean.getSelectedCampus();
		}
		 if(location != null){
		    	loc =location.toString();
		    }else{
		    	loc = Long.toString(formBean.getLocation().getLmsKey());
		    	}
		 
		List<Domain> domainList = getDomainsByLocation(loc);
		
	
			
		List<ActivityType> activityTypeList = getAllActivityTypes(); 
		List<Status> statusList = getAllStatus();
		formBean.setActivityTypes(activityTypeList);
		formBean.setLocations(locationList);
		formBean.setDomains(domainList);
		formBean.setStatusType(statusList);
	}

	@Override
	public IRunAndRenderTask getBirtTask(HttpServletRequest request,
			HttpServletResponse response, ParameterPageUi formBean) throws Exception  {
		       String reportType = formBean.getReportType();
		    LOG.info("report format " + reportType);
			ServletContext sc = request.getSession().getServletContext();
			IReportEngine birtReportEngine = BirtEngine.getBirtEngine(sc);
			 String reportName = sc.getRealPath("/report")+FILE_DELIM + formBean.getReportFile(); 
			//Open report design
			 IReportRunnable design = birtReportEngine.openReportDesign(reportName);
				//create task to run and render report
			 return birtReportEngine.createRunAndRenderTask( design );
				
	}

	@Override
	public void createReport(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, IRunAndRenderTask task)
	throws Exception{
		
		ServletContext sc = request.getSession().getServletContext();
		if(formBean.getReportType().equals("pdf")){
        	PDFRenderOption options = new PDFRenderOption();
        	options.setOutputFormat("pdf");
            options.setImageHandler(new HTMLServerImageHandler());
			options.setOutputStream(response.getOutputStream());
			task.setRenderOption(options);
		
			
        }else{
        	HTMLRenderOption options = new HTMLRenderOption();
        	options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
            options.setImageHandler(new HTMLServerImageHandler());
			options.setOutputStream(response.getOutputStream());
			options.setBaseImageURL(request.getContextPath()+"/images");
			options.setImageDirectory(sc.getRealPath("/images"));
			options.setHtmlTitle(formBean.getReportName());
			task.setRenderOption(options);
        }
	 
	 
	//run report
		task.run();
		task.close();
		
	}

	@Override
	public String  initParamPage(HttpServletRequest request,
			ParameterPageUi formBean, Model model) {
		if(request.getSession() == null){
			return "login";
		}
		
	
		Person person=(Person)request.getSession().getAttribute("person");
		if(person == null){
			return "login";
		}
		
		
		 formBean.setLocation(person.getLocation());
	     formBean.setUserRole(person.getRoleName());
	     formBean.setReportFile(request.getParameter("report"));
	  
	     getBasicDropdowns(formBean);
	     //if(person.getRoleName().equalsIgnoreCase("LimitedUser")){
	    	 //formBean.setActivityList(person.getActivityList());
	    	 
	    // }
	     model.addAttribute(formBean);
		 return null;
	}

	@Override
	public String reloadPage(HttpServletRequest request, HttpServletResponse response, ParameterPageUi formBean, BindingResult result, Model model, Person person) throws Exception {
		if (request.getSession() == null) {
			return "login";
		}

		

		if (person == null) {
			return "login";

		}

		formBean.setLocation(person.getLocation());
		formBean.setUserRole(person.getRoleName());
        StringBuilder bldr = null;
		String activityType = null;

		if (formBean.getActivityType() != null) {
			bldr = new StringBuilder(formBean.getActivityType()
					.toString());
			bldr.deleteCharAt(0)
					.deleteCharAt(bldr.length() - 1);
			
			activityType = bldr.toString();
		}
		

			
			getBasicDropdowns(formBean);

			if (formBean.getReportFile() == null)
				formBean.setReportFile(request.getParameter("report"));
			model.addAttribute("formBean", formBean);

			String activityLocation = null;
			if (formBean.getUserRole().equals("SuperUser")) {
				activityLocation = formBean.getSelectedCampus();
			} else {
				activityLocation = Long.toString(formBean.getLocation()
						.getTopLevelLMSKey());
			}

		
			     StringBuilder sbLimited = new StringBuilder();
				List<Activity> activity = null;
				if(person.getRoleName().equalsIgnoreCase("LimitedUser")){
					
					List<Activity> limitedActivities = person.getActivityList();
					for(Activity act: limitedActivities){
						sbLimited.append(act.getActivityId());
						sbLimited.append(",");
						
					}
					sbLimited.deleteCharAt(sbLimited.length()-1);
		    	
		    	 
		         }
				if(formBean.getReportName().equalsIgnoreCase("upcomingActivities")){
				        activity = getFutureActivities(
						formBean.getFromDate(), formBean.getToDate(),
						activityLocation, activityType,
						formBean.getOrgCd(), formBean.getActivityName(),
						formBean.getCourseCode(), sbLimited.toString());
				} else {
					
					activity = getActivities(formBean.getFromDate(), formBean.getToDate(), 
							activityLocation, activityType, formBean.getOrgCd(), formBean.getActivityName(), 
							formBean.getCourseCode(), sbLimited.toString());
				}
			   
				if (activity.size() > MAX_SIZE) {
					result.reject("TooMuchData");
					return formBean.getReportName();
				}

				formBean.setActivityList(activity);
		
			return  formBean.getReportName();
		
	}

	@Override
	public String changeCampus(HttpServletRequest request,
			ParameterPageUi formBean, Person person) {
		if (request.getSession() == null) {
			return "login";
		}

		if (person == null) {
			return "login";

		}
		formBean.setLocation(person.getLocation());
		formBean.setUserRole(person.getRoleName());
		
		getBasicDropdowns(formBean);
		return formBean.getReportName();
	}

	


	
	

		
	
		

		
	

	

}
