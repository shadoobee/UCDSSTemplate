package edu.ucdavis.ss.lmsreports.birt.handler;

import java.sql.ResultSet;

import java.util.List;

import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.engine.api.script.IUpdatableDataSetRow;
import org.eclipse.birt.report.engine.api.script.ScriptException;
import org.eclipse.birt.report.engine.api.script.eventadapter.ScriptedDataSetEventAdapter;
import org.eclipse.birt.report.engine.api.script.instance.IDataSetInstance;

import edu.ucdavis.ss.lmsreports.domain.Learner;



public class ComplianceByActivityHandler extends ScriptedDataSetEventAdapter {
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
		            row.setColumnValue("PRIMARYDOMAIN", reportData.get(count).getLearnerPrimaryDomain());
		            row.setColumnValue("ACTIVITYCODE", reportData.get(count).getActivityCode());
		            row.setColumnValue("LEARNER", reportData.get(count).getLearner());
		            row.setColumnValue("LEARNEREMAIL", reportData.get(count).getLearnerEmail());
		            row.setColumnValue("MANAGER", reportData.get(count).getManager());
		            row.setColumnValue("ACTIVITY", reportData.get(count).getActivity());
		            row.setColumnValue("DUEDATE", reportData.get(count).getStrDueDate());
		            row.setColumnValue("COMPLETIONDATE", reportData.get(count).getStrCompletionDate());
		            row.setColumnValue("EXPIRATIONDATE", reportData.get(count).getStrExpirationDate());
		            row.setColumnValue("USERSTATUS", reportData.get(count).getLearnerStatus());
		            row.setColumnValue("USERCD", reportData.get(count).getUserCd());
		            row.setColumnValue("TRAININGDUEDATE", reportData.get(count).getStrTraningDueDate());
		            row.setColumnValue("EMPLOYEEID", reportData.get(count).getLearnerEmployeeNumber());
		            count++;
		        
		            return true;
		        }
		    } catch (ScriptException e) {
		        e.printStackTrace();
		    }
		  return super.fetch(dataSet, row);
	}

	@Override
	public void open(IDataSetInstance dataSet) throws ScriptException {
		// TODO Auto-generated method stub
		super.open(dataSet);
	}

	@Override
	public void beforeOpen(IDataSetInstance dataSet,
			IReportContext reportContext) throws ScriptException {
		// TODO Auto-generated method stub
		super.beforeOpen(dataSet, reportContext);
		
		reportData = (List<Learner>)reportContext.getParameterValue("resultSet");
		MAX_NUM = reportData.size();
	}

}
