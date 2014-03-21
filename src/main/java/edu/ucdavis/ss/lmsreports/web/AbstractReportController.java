package edu.ucdavis.ss.lmsreports.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.ucdavis.ss.lmsreports.domain.Learner;
import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.service.LMSReportService;
import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;

public abstract class AbstractReportController {
	
	@Autowired
    private LMSReportService lmsReportService;

	private static Logger LOG = Logger.getLogger(UpcomingActivitiesController.class); 
	private static final int MAX_SIZE = 1000;
	
	
	public String upcomingActivitiesParamPage(@ModelAttribute("formBean") ParameterPageUi formBean,BindingResult result,  Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
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
	  
	     lmsReportService.getBasicDropdowns(formBean);
	     model.addAttribute(formBean);
		return  null;
	}

}
