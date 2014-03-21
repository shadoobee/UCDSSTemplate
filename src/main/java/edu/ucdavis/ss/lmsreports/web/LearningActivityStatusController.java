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
import edu.ucdavis.ss.lmsreports.domain.Activity;
import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.service.LMSReportService;
import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;


@Controller
public class LearningActivityStatusController {
	
	   
	private static Logger LOG = Logger.getLogger(LearningActivityStatusController.class); 
    
    @Autowired
    private LMSReportService lmsReportService;

    
	@RequestMapping(value = { "learningActivityStatus.htm" }, method = RequestMethod.POST)
	public String learningActiviytStatus(@ModelAttribute("formBean") @Valid ParameterPageUi formBean,BindingResult result,  Model model,
	HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		
		Person person = (Person) request.getSession().getAttribute("person");	
		formBean.setReportName("learningActivityStatus");
		
		String reportType = formBean.getReportType();
		
		if (reportType.equalsIgnoreCase("changeCampus")){
			return lmsReportService.changeCampus(request, formBean, person);
		} else if (reportType.equalsIgnoreCase("findActivities")){
			
           return lmsReportService.reloadPage(request, response, formBean, result, model, person);
		
		
		} else if (reportType.equalsIgnoreCase("excel")) {

			LOG.info("Running report " + formBean.getReportFile()
					+ " for user " + person.getFullName());
			List<Activity> reportData = lmsReportService
					.getLearningActivityStatusData(formBean);

			if (reportData == null || reportData.size() == 0) {
				result.reject("NoData");
			    return lmsReportService.reloadPage(request, response, formBean, result, model, person);
			}

			model.addAttribute("reportData", reportData);
			model.addAttribute("reportName", "LearningActivityStatus");
			model.addAttribute("formBean", formBean);
			response.setHeader("Content-Disposition",
					"inline; filename=LearningActivityStatus.xls");
			return "excelOutput";

		} else {
			// run the report
			LOG.info("Running report " + formBean.getReportFile()
					+ " for user " + person.getFullName());
			List<Activity> reportData = lmsReportService
					.getLearningActivityStatusData(formBean);
			if (reportType.equalsIgnoreCase("pdf")) {
				response.setContentType("application/pdf");
			}

			lmsReportService.createLearningActivityStatusReport(request,
					response, formBean, reportData);
			return null;
		}

	        
	}
	
	
	@RequestMapping(value = { "learningActivityStatus.htm" }, method = RequestMethod.GET)
	public String learningActivityStatusParamPage(@ModelAttribute("formBean") ParameterPageUi formBean,BindingResult result,  Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String page = lmsReportService.initParamPage(request, formBean, model);
		if(page != null){
			return page;
		} else {
		return "learningActivityStatus";
		}

		

	}

     
}
