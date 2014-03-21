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

import edu.ucdavis.ss.lmsreports.domain.Learner;
import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.service.LMSReportService;
import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;

@Controller
public class ComplianceByActivityController {
	
	   

    
    @Autowired
    private LMSReportService lmsReportService;

	private static Logger LOG = Logger.getLogger(ComplianceByActivityController.class); 

	
	
	
	/**
	 * @param formBean
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "complianceByActivity.htm" }, method = RequestMethod.POST)
	public String complianceByActivity(@ModelAttribute("formBean") @Valid ParameterPageUi formBean,BindingResult result,  Model model,
	HttpServletRequest request, HttpServletResponse response )
	throws Exception {

		Person person = (Person) request.getSession().getAttribute("person");	
		formBean.setReportName("complianceByActivity");
		String reportType = formBean.getReportType();
		
		if (reportType.equalsIgnoreCase("changeCampus")){
			return lmsReportService.changeCampus(request, formBean, person);
		} else 	if (reportType.equalsIgnoreCase("findActivities")) {
           return lmsReportService.reloadPage(request, response, formBean, result, model, person);
		
		} else if (reportType.equalsIgnoreCase("excel")) {

			LOG.info("Running report " + formBean.getReportFile()
					+ " for user " + person.getFullName());
			List<Learner> reportData = lmsReportService
					.getComplianceByActivityReportData(formBean);

			if (reportData == null || reportData.size() == 0) {
				result.reject("NoData");
			    return lmsReportService.reloadPage(request, response, formBean, result, model, person);
			}


			model.addAttribute("reportData", reportData);
			model.addAttribute("reportName", "ComplianceByActivity");
			model.addAttribute("formBean", formBean);
			response.setHeader("Content-Disposition",
					"inline; filename=ComplianceByActivity.xls");
			return "excelOutput";

		} else {
			// run the report
			LOG.info("Running report  " + formBean.getReportFile()
					+ " for user " + person.getFullName());
			List<Learner> reportData = lmsReportService
					.getComplianceByActivityReportData(formBean);
			if (reportType.equalsIgnoreCase("pdf")) {
				response.setContentType("application/pdf");
			}

			lmsReportService.createComplianceByActivityReport(request,
					response, formBean, reportData);
			return null;
		}
		
	
	}
	
	
	
	@RequestMapping(value = { "complianceByActivity.htm" }, method = RequestMethod.GET)
	public String ComplianceByActivityParamPage(@ModelAttribute("formBean") ParameterPageUi formBean,BindingResult result,  Model model,
		HttpServletRequest request, HttpServletResponse response) throws Exception{
		String page = lmsReportService.initParamPage(request, formBean, model);
		if(page != null){
			return page;
		} else {
		return "complianceByActivity";
		}

	
	}

}
