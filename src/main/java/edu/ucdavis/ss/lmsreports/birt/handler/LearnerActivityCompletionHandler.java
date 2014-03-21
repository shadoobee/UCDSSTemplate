package edu.ucdavis.ss.lmsreports.birt.handler;

import java.sql.ResultSet;

import java.util.List;

import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.engine.api.script.IUpdatableDataSetRow;
import org.eclipse.birt.report.engine.api.script.ScriptException;
import org.eclipse.birt.report.engine.api.script.eventadapter.ScriptedDataSetEventAdapter;
import org.eclipse.birt.report.engine.api.script.instance.IDataSetInstance;


import edu.ucdavis.ss.lmsreports.domain.Learner;


public class LearnerActivityCompletionHandler extends
		ScriptedDataSetEventAdapter {
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
	        	row.setColumnValue("ActivityName", reportData.get(count).getActivity());
	            row.setColumnValue("Learner", reportData.get(count).getLearner());
	            row.setColumnValue("CompletionDate", reportData.get(count).getStrCompletionDate());
	            row.setColumnValue("OrgName", reportData.get(count).getLearnerPrimaryDomain());
	            row.setColumnValue("Status", reportData.get(count).getLearnerStatus());
	            row.setColumnValue("Manager", reportData.get(count).getManager());
	            row.setColumnValue("LearnerOrgCd", reportData.get(count).getLearnerOrgCd());
	            row.setColumnValue("HomeDepartment", reportData.get(count).getLearnerPrimaryOrg());
	            row.setColumnValue("LearnerEmail", reportData.get(count).getLearnerEmail());
	            row.setColumnValue("LearnerUserNumber", reportData.get(count).getLearnerNumber());
	            row.setColumnValue("LearnerEmployeeNumber", reportData.get(count).getLearnerEmployeeNumber());
	            row.setColumnValue("JobName", reportData.get(count).getLearnerJobTitle());
	            row.setColumnValue("CompletionStatus",  reportData.get(count).getCompletionStatus());
	            row.setColumnValue("UserCd",  reportData.get(count).getUserCd());
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
		// TODO Auto-generated method stub
		super.beforeOpen(dataSet, reportContext);
		
		reportData = (List<Learner>)reportContext.getParameterValue("resultSet");
		MAX_NUM = reportData.size();
	}

}
