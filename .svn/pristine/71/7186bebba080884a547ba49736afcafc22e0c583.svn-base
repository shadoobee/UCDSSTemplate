package edu.ucdavis.ss.lmsreports.birt.handler;

import java.sql.ResultSet;

import java.util.List;

import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.engine.api.script.IUpdatableDataSetRow;
import org.eclipse.birt.report.engine.api.script.ScriptException;
import org.eclipse.birt.report.engine.api.script.eventadapter.ScriptedDataSetEventAdapter;
import org.eclipse.birt.report.engine.api.script.instance.IDataSetInstance;

import edu.ucdavis.ss.lmsreports.domain.Learner;

public class ComplianceHandler extends ScriptedDataSetEventAdapter {
	List<Learner> reportData;
	ResultSet resultset;
	int count = 0;
	int MAX_NUM;
	
	@Override
	public boolean fetch(IDataSetInstance dataSet, IUpdatableDataSetRow row)
			throws ScriptException {
	    try {
	    	
	    	if (MAX_NUM == 0 ) {
	    	
	    		row.setColumnValue(	1, "We're sorry. There are no rows to display.");
	    		return false;
	    	}
	    	
	    	
	        if (count < MAX_NUM)
	        {
	            row.setColumnValue("LEARNER", reportData.get(count).getLearner());
	            row.setColumnValue("EMPLOYEEID", reportData.get(count).getLearnerEmployeeNumber());
	            row.setColumnValue("HOMEDEPARTMENT", reportData.get(count).getLearnerPrimaryDomain());
	            row.setColumnValue("ACTIVITYNAME", reportData.get(count).getActivity());
	            row.setColumnValue("DUEDATE", reportData.get(count).getDueDate());
	            row.setColumnValue("STATUS", reportData.get(count).getCertStatus());
	            row.setColumnValue("CERTAWARDDATE", reportData.get(count).getCompletionDate());
	            row.setColumnValue("EMAIL", reportData.get(count).getLearnerEmail());
	            row.setColumnValue("ORGNAME", reportData.get(count).getLearnerPrimaryOrg());
	            row.setColumnValue("USERCODE",reportData.get(count).getUserCd());
	            row.setColumnValue("EXPIRATIONDATE", reportData.get(count).getExpirationDate());
	            row.setColumnValue("TRAININGDUEDATE", reportData.get(count).getTrainingDueDate());
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
		super.open(dataSet);
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void beforeOpen(IDataSetInstance dataSet,
			IReportContext reportContext) throws ScriptException {
		super.beforeOpen(dataSet, reportContext);
		
	
		reportData = (List<Learner>)reportContext.getParameterValue("resultSet");
		MAX_NUM = reportData.size();
		
	}

}
