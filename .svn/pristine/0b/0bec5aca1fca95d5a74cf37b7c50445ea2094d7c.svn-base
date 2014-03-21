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
import edu.ucdavis.ss.lmsreports.domain.Learner;
import edu.ucdavis.ss.lmsreports.domain.Person;
import edu.ucdavis.ss.lmsreports.service.LMSReportService;
import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;


@Controller
public class LearningActivityCompletionController {
	
	
    

   
   @Autowired
   private LMSReportService lmsReportService;

	private static Logger LOG = Logger.getLogger(LearningActivityCompletionController.class); 
	
	
	@RequestMapping(value = { "learningActivityCompletions.htm" }, method = RequestMethod.POST)   
	String learningActivityCompletions(@ModelAttribute("formBean") @Valid ParameterPageUi formBean,BindingResult result,  Model model,
	HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		
		Person person = (Person) request.getSession().getAttribute("person");	
		formBean.setReportName("learningActivityCompletions");
		
		String reportType = formBean.getReportType();
		
		if (reportType.equalsIgnoreCase("changeCampus")){
			return lmsReportService.changeCampus(request, formBean, person);
		} else if (reportType.equalsIgnoreCase("findActivities")){
			return lmsReportService.reloadPage(request, response, formBean, result, model, person);
		} else if (reportType.equalsIgnoreCase("excel")) {

			List<Activity> reportData = lmsReportService
					.getLearningActivityCompletionData(formBean);

			if (reportData == null || reportData.size() == 0) {
				result.reject("NoData");
			    return lmsReportService.reloadPage(request, response, formBean, result, model, person);
			}

			response.setHeader("Content-Disposition",
					"inline; filename=LearningActivityCompletions.xls");
			model.addAttribute("reportData", reportData);
			model.addAttribute("reportName", "LearningActivityCompletion");
			model.addAttribute("formBean", formBean);
			return "excelOutput";

		} else {
			LOG.info("Running report " + formBean.getReportFile()
					+ " for user " + person.getFullName());

			List<Activity> reportData = lmsReportService
					.getLearningActivityCompletionData(formBean);

			if (reportType.equalsIgnoreCase("pdf")) {
				response.setContentType("application/pdf");
			}
			lmsReportService.createLearningActivityCompletionReport(request,
					response, formBean, reportData);

			return null;
		}
	
		
	}
	
	
	
	@RequestMapping(value = { "learningActivityCompletions.htm" }, method = RequestMethod.GET)   
	public String LearningActivityCompletionParamPage(@ModelAttribute("formBean") ParameterPageUi formBean,BindingResult result,  Model model,
	HttpServletRequest request, HttpServletResponse response) throws Exception{
		String page = lmsReportService.initParamPage(request, formBean, model);
		if(page != null){
			return page;
		} else {
		return "learningActivityCompletions";
		}
		
	}
	
	
	@RequestMapping(value = {"learningActivityDetails.htm" }, method = RequestMethod.GET)
	public void learningActivityDetails(@ModelAttribute("formBean") ParameterPageUi formBean, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
      String activityId = request.getParameter("actId");
      String reportType = request.getParameter("reportType");
      String userCd = request.getParameter("userCd");
      String orgCd = request.getParameter("orgCd");
      String domainId = request.getParameter("domainId");
      List<Learner> reportData = lmsReportService.getLearningActivityDetail(activityId, domainId, userCd, orgCd);
      if(reportType.equalsIgnoreCase("pdf")){
			   response.setContentType("application/pdf");
	  }
    
      formBean.setReportType(reportType);
      formBean.setReportFile("LearningActivityDetail.rptdesign");
      formBean.setReportName("Learning Activity Detail");
      
	  lmsReportService.createLearningActivityDetailReport(request, response, formBean, reportData);
	}
	

}
