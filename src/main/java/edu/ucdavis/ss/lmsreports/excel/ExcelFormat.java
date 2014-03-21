package edu.ucdavis.ss.lmsreports.excel;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import edu.ucdavis.ss.lmsreports.domain.Activity;
import edu.ucdavis.ss.lmsreports.domain.Learner;
import edu.ucdavis.ss.lmsreports.domain.RosterReport;
import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;


public class ExcelFormat extends AbstractExcelView {
    ParameterPageUi formBean = null;
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String reportName = (String)model.get("reportName");
		       formBean = (ParameterPageUi)model.get("formBean");
		List<?> reportData = (List<?>)model.get("reportData");
		 
		if (reportName.equalsIgnoreCase("ComplianceByActivity")){
			createComplianceByActivity(workbook, reportData);
		}else if(reportName.equalsIgnoreCase("ComplianceByLearner")){
			createComplianceByLearner(workbook, reportData);
		}else if(reportName.equalsIgnoreCase("LearningActivityCompletion")){
			createLearningActivityCompletion(workbook, reportData);
		}else if(reportName.equalsIgnoreCase("Roster")){
			createRoster(workbook, reportData);
		}else if (reportName.equalsIgnoreCase("UpcomingActivities")){
			createUpcomingActivities(workbook, reportData);
		} else if (reportName.equalsIgnoreCase("LearningActivityStatus")){
			createLearningActivityStatus(workbook, reportData);
		}
		
		
		
		
	}	
	
	

	
	private void createLearningActivityStatus( HSSFWorkbook workbook, List<?> reportData){
        HSSFSheet sheet = workbook.createSheet(formBean.getReportName());
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Activity Name");
		header.createCell(1).setCellValue("Primary Domain");
		header.createCell(2).setCellValue("Activity Code");
		header.createCell(3).setCellValue("Activity Type/Label");
		header.createCell(4).setCellValue("Start Date");
		header.createCell(5).setCellValue("End Date");
		header.createCell(6).setCellValue("Activity Status");
		header.createCell(7).setCellValue("Registered");
		header.createCell(8).setCellValue("Attended");
		header.createCell(9).setCellValue("Wait List");
		header.createCell(10).setCellValue("Cancelled");
		header.createCell(11).setCellValue("No Show");
		header.createCell(12).setCellValue("Expressed Interest");
		header.createCell(13).setCellValue("Minimum Capacity");
		header.createCell(14).setCellValue("Maximum Capacity");
		
		int rowNum = 1;
		for (Object obj  : reportData) {
			//create the row data
		    Activity r  = (Activity)obj;	
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(r.getActivityName());
			row.createCell(1).setCellValue(r.getPrimaryDomain());
			row.createCell(2).setCellValue(r.getActivityCd());
			row.createCell(3).setCellValue(r.getActivityType());
			row.createCell(4).setCellValue(formatDate(r.getStartDt()));
			row.createCell(5).setCellValue(formatDate(r.getEndDt()));
			row.createCell(6).setCellValue(r.getStatus());
			row.createCell(7).setCellValue(r.getRegisteredCount());
			row.createCell(8).setCellValue(r.getAttendedCount());
			row.createCell(9).setCellValue(r.getWaitListCount());
			row.createCell(10).setCellValue(r.getCancelledCount());
			row.createCell(11).setCellValue(r.getNoShowCount());
			row.createCell(12).setCellValue(r.getExpressedInterestCount());
			row.createCell(13).setCellValue(r.getMinCapacity() );
			row.createCell(14).setCellValue(r.getMaxCapacity());
			
		
	    }
		
		
	}
	
  
	private void createComplianceByLearner( HSSFWorkbook workbook, List<?> reportData){
		HSSFSheet sheet = workbook.createSheet(formBean.getReportName());
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Learner Information");
		header.createCell(1).setCellValue("Employee ID");
		header.createCell(2).setCellValue("Department");
		header.createCell(3).setCellValue("Assigned Training");
		header.createCell(4).setCellValue("Certification Status");
		header.createCell(5).setCellValue("Certification Award Date");
		header.createCell(6).setCellValue("User Code");
		header.createCell(7).setCellValue("Email");
		header.createCell(8).setCellValue("Due Date");
		header.createCell(9).setCellValue("Expiration Date");
		header.createCell(10).setCellValue("Training Due Date");
		
		int rowNum = 1;
		for (Object obj  : reportData) {
			//create the row data
		    Learner r  = (Learner)obj;	
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(r.getLearner());
			row.createCell(1).setCellValue(r.getLearnerEmployeeNumber());
			row.createCell(2).setCellValue(r.getLearnerPrimaryOrg());
			row.createCell(3).setCellValue(r.getActivity());
			row.createCell(4).setCellValue(r.getCertStatus());
			row.createCell(5).setCellValue(formatDate(r.getCompletionDate()));
			row.createCell(6).setCellValue(r.getUserCd());
			row.createCell(7).setCellValue(r.getLearnerEmail());
			row.createCell(8).setCellValue(formatDate(r.getDueDate()));
			row.createCell(9).setCellValue(formatDate(r.getExpirationDate()));
			row.createCell(10).setCellValue(formatDate(r.getTrainingDueDate()));
			
		
	    }
		
		
	}
	
	
	private void createComplianceByActivity (HSSFWorkbook workbook, List<?> reportData ){
		HSSFSheet sheet = workbook.createSheet(formBean.getReportName());
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Activity Name");
		header.createCell(1).setCellValue("Primary Organization");
		header.createCell(2).setCellValue("User Code");
		header.createCell(3).setCellValue("Activity Code");
		header.createCell(4).setCellValue("Employee Name");
		header.createCell(5).setCellValue("Email Address");
		header.createCell(6).setCellValue("Employee ID");
		header.createCell(7).setCellValue("Manager Name");
		header.createCell(8).setCellValue("Due Date");
		header.createCell(9).setCellValue("Completion Date");
		header.createCell(10).setCellValue("Expiration Date");
		header.createCell(11).setCellValue("Training Due Date");
		header.createCell(12).setCellValue("Status");
		
	
		
		int rowNum = 1;
		for (Object obj  : reportData) {
			//create the row data
			Learner r  = (Learner)obj;	
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(r.getActivity());
			row.createCell(1).setCellValue(r.getLearnerPrimaryDomain());
			row.createCell(2).setCellValue(r.getUserCd());
			row.createCell(3).setCellValue(r.getActivityCode());
			row.createCell(4).setCellValue(r.getLearner());
			row.createCell(5).setCellValue(r.getLearnerEmail());
			row.createCell(6).setCellValue(r.getLearnerEmployeeNumber());
			row.createCell(7).setCellValue(r.getManager());
			row.createCell(8).setCellValue(formatDate(r.getDueDate()));
			row.createCell(9).setCellValue(formatDate(r.getCompletionDate()));
			row.createCell(10).setCellValue(formatDate(r.getExpirationDate()));
			row.createCell(11).setCellValue(formatDate(r.getTrainingDueDate()));
		    row.createCell(12).setCellValue(r.getLearnerStatus());
			
		
	    }
		
	}
	
	
	private void createRoster(HSSFWorkbook workbook, List<?> reportData){
		HSSFSheet sheet = workbook.createSheet(formBean.getReportName());
		
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Learner Name");
		header.createCell(1).setCellValue("Employee ID");
		header.createCell(2).setCellValue("Home Department/Primary Domain");
		header.createCell(3).setCellValue("Email");
		header.createCell(4).setCellValue("Phone");
		header.createCell(5).setCellValue("Signature");
		if(reportData==null || reportData.size() == 0){
			return;
		}
		int rowNum = 1;
		for(Object obj :  reportData){
			HSSFRow row = sheet.createRow(rowNum++);
			RosterReport r = (RosterReport)obj;
			row.createCell(0).setCellValue(r.getLearner());
			row.createCell(1).setCellValue(r.getEmployeeID());
			row.createCell(2).setCellValue(r.getHomeDept());
			row.createCell(3).setCellValue(r.getEmail());
			row.createCell(4).setCellValue(r.getPhone());
			
		}
	}

	
	private void createLearningActivityCompletion(HSSFWorkbook workbook, List<?> reportData){
		HSSFSheet sheet = workbook.createSheet(formBean.getReportName());
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Activity");
		header.createCell(1).setCellValue("Primary Domain");
		header.createCell(2).setCellValue("Activity Type");
		header.createCell(3).setCellValue("Category");
		header.createCell(4).setCellValue("Activity Code");
		header.createCell(5).setCellValue("Start Date");
		header.createCell(6).setCellValue("End Date");
		header.createCell(7).setCellValue("Registered");
		header.createCell(8).setCellValue("Attended");
		header.createCell(9).setCellValue("Completed");
		header.createCell(10).setCellValue("Completed Single Headcount");
		
		
		int rowNum = 1;
		for(Object obj :  reportData){
			HSSFRow row = sheet.createRow(rowNum++);
			Activity r= (Activity)obj;
			row.createCell(0).setCellValue(r.getActivityName());
			row.createCell(1).setCellValue(r.getPrimaryDomain());
			row.createCell(2).setCellValue(r.getActivityType());
			if(r.getCategory() != null){
				row.createCell(3).setCellValue(r.getCategory());
			}
			row.createCell(4).setCellValue(r.getActivityCd());
			row.createCell(5).setCellValue(formatDate(r.getStartDt()));
			row.createCell(6).setCellValue(formatDate(r.getEndDt()));
			row.createCell(7).setCellValue(r.getRegisteredCount());
			row.createCell(8).setCellValue(r.getAttendedCount());
			row.createCell(9).setCellValue(r.getCompletedCount());
			row.createCell(10).setCellValue(r.getCompletedSingleHeadcount());
	}
	
	
	
	}
	
	
	
	private void createUpcomingActivities( HSSFWorkbook workbook, List<?> reportData){
		HSSFSheet sheet = workbook.createSheet(formBean.getReportName());
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Learning Activity");
		header.createCell(1).setCellValue("Domain");
		header.createCell(2).setCellValue("Activity Code");
		header.createCell(3).setCellValue("Activity Type");
		header.createCell(4).setCellValue("Start Date");
		header.createCell(5).setCellValue("Start Time");
		header.createCell(6).setCellValue("End Date");
		header.createCell(7).setCellValue("End Time");
		header.createCell(8).setCellValue("Cancelled");
		header.createCell(9).setCellValue("Registered");
		
		int rowNum = 1;
		for (Object obj  : reportData) {
			//create the row data
			Activity  r  = (Activity )obj;	
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(r.getActivityName());
			row.createCell(1).setCellValue(r.getPrimaryDomain());
			row.createCell(2).setCellValue(r.getActivityCd());
			row.createCell(3).setCellValue(r.getActivityType());
		    row.createCell(4).setCellValue(formatDate(r.getStartDt()));
		    row.createCell(5).setCellValue(formatTime(r.getStartDt()));
			row.createCell(6).setCellValue(formatDate(r.getEndDt()));
			row.createCell(7).setCellValue(formatTime(r.getEndDt()));
			row.createCell(8).setCellValue(r.getCancelledCount());
			row.createCell(9).setCellValue(r.getRegisteredCount());
			
		
	    }
		
		
	}
	
	
	private String formatDate(Date date){
		if(date == null){
			return "";
		}
		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
		return fmt.format(date);
	}
	
	
	private String formatTime(Date date){
		if(date == null){
			return "";
		}
		SimpleDateFormat fmt = new SimpleDateFormat("hh:mm a");
		return fmt.format(date);
	}

}
