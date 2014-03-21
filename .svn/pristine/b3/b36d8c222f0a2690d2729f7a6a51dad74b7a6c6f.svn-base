package edu.ucdavis.ss.lmsreports.birt.handler;

import java.sql.ResultSet;

import java.util.List;

import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.engine.api.script.IUpdatableDataSetRow;
import org.eclipse.birt.report.engine.api.script.ScriptException;
import org.eclipse.birt.report.engine.api.script.eventadapter.ScriptedDataSetEventAdapter;
import org.eclipse.birt.report.engine.api.script.instance.IDataSetInstance;


import edu.ucdavis.ss.lmsreports.domain.Learner;



public class UpcomingActivityDetails extends ScriptedDataSetEventAdapter {
	
	
	List<Learner> reportData;
	ResultSet resultset;
	int count = 0;
	int MAX_NUM;
	@Override
	public boolean fetch(IDataSetInstance dataSet, IUpdatableDataSetRow row)
			throws ScriptException {
		 try {
		    	
		    	
		    	
		    	
		        if (count < MAX_NUM)
		        {  
		            row.setColumnValue("LEARNINGACTIVITY", reportData.get(count).getActivity());
		            row.setColumnValue("USERPRIMARYDOMAIN", reportData.get(count).getLearnerPrimaryDomain());
		            row.setColumnValue("USERSTATUS", reportData.get(count).getLearnerStatus());
		            row.setColumnValue("FULLNAME", reportData.get(count).getLearner());
		            row.setColumnValue("MANAGERFULLNAME", reportData.get(count).getManager());
		            row.setColumnValue("USERORGCODE", reportData.get(count).getLearnerOrgCd());
		            row.setColumnValue("USERPRIMARYORG", reportData.get(count).getLearnerPrimaryOrg());
		            row.setColumnValue("USERNUMBER", reportData.get(count).getLearnerNumber());
		            row.setColumnValue("EMPLOYEENUMBER", reportData.get(count).getLearnerEmployeeNumber());
		            row.setColumnValue("USERPRIMARYJOBTITLE",  reportData.get(count).getLearnerJobTitle());
		            row.setColumnValue("REGISTRATIONSTATUS", reportData.get(count).getRegistrationStatus());
		            row.setColumnValue("USERCD", reportData.get(count).getUserCd());
		           
		            count++;
		        
		            return true;
		        }
		    } catch (ScriptException e) {
		        e.printStackTrace();
		    }
		return super.fetch(dataSet, row);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void beforeOpen(IDataSetInstance dataSet,
			IReportContext reportContext) throws ScriptException {
		
		reportData = (List<Learner>)reportContext.getParameterValue("resultSet");
		MAX_NUM = reportData.size();
	}
	

}
