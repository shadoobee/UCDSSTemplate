package edu.ucdavis.ss.lmsreports.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.domain.RosterReport;
import edu.ucdavis.ss.lmsreports.service.LMSReportService;
import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;


@Controller
public class RosterController {
	

    
    @Autowired
    private LMSReportService lmsReportService;

	private static Logger LOG = Logger.getLogger(RosterController.class); 

	
	@RequestMapping(value = { "roster.htm" }, method = RequestMethod.GET)
	public String RosterParamPage(@ModelAttribute("formBean") @Valid ParameterPageUi formBean,BindingResult result,  Model model,
			HttpServletRequest request, HttpServletResponse response )throws Exception{
		
		String page = lmsReportService.initParamPage(request, formBean, model);
		if(page != null){
			return page;
		} else {
		return "roster";
		}
		
	}
	
	@RequestMapping(value = { "roster.htm" }, method = RequestMethod.POST)
	public String roster(@ModelAttribute("formBean") @Valid ParameterPageUi formBean,BindingResult result,  Model model,
			HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		
		Person person = (Person) request.getSession().getAttribute("person");	
		formBean.setReportName("roster");
		
		String reportType = formBean.getReportType();
		
		if (reportType.equalsIgnoreCase("changeCampus")){
			return lmsReportService.changeCampus(request, formBean, person);
		} else if (reportType.equalsIgnoreCase("findActivities")){
			return lmsReportService.reloadPage(request, response, formBean, result, model, person);
		}else if (reportType.equalsIgnoreCase("excel")){
			   
			  
			   LOG.info("Running report " + formBean.getReportFile() + " for user " + person.getFullName());
			   List<RosterReport> activityReportData = lmsReportService.getRosterActivityData(formBean);
			   if(activityReportData==null || activityReportData.size() == 0){
					  result.reject("NoData");	
					  return lmsReportService.reloadPage(request, response, formBean, result, model, person);
				  }
			   List<RosterReport> learnerReportData = lmsReportService.getRosterLearnerData(formBean);
			   if(learnerReportData==null || learnerReportData.size() == 0){
					  result.reject("NoData");	
					  return lmsReportService.reloadPage(request, response, formBean, result, model, person);
				  }
			   List<RosterReport> sessionData = lmsReportService.getRosterSessionData(formBean);
			   if(sessionData==null || sessionData.size() == 0){
					  result.reject("NoData");	
					  return lmsReportService.reloadPage(request, response, formBean, result, model, person);
				  }
				
				   model.addAttribute("activityReportData", activityReportData);
				   model.addAttribute("reportData", learnerReportData);
				   model.addAttribute("reportName", "Roster");
				   model.addAttribute("formBean", formBean);
				   response.setHeader("Content-Disposition", "inline; filename=Roster.xls");
				    return "excelOutput";
			  
			   
			   
	   } else {
		   LOG.info("Running report " + formBean.getReportFile() + " for user " + person.getFullName());
		     List<RosterReport> activityReportData = lmsReportService.getRosterActivityData(formBean);
		     List<RosterReport> learnerReportData = lmsReportService.getRosterLearnerData(formBean);
		     List<RosterReport> sessionData = lmsReportService.getRosterSessionData(formBean);
			   if(reportType.equalsIgnoreCase("pdf")){
				   response.setContentType("application/pdf");
			   }
			
			   lmsReportService.createRosterReport(request, response, formBean, activityReportData, learnerReportData, sessionData);
			   return null;
		   
	   }
		
			
	}	


}
