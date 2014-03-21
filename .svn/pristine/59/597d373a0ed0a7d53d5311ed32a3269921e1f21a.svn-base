package edu.ucdavis.ss.lmsreports.birt.handler;

import java.sql.ResultSet;

import java.util.List;

import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.engine.api.script.IUpdatableDataSetRow;
import org.eclipse.birt.report.engine.api.script.ScriptException;
import org.eclipse.birt.report.engine.api.script.eventadapter.ScriptedDataSetEventAdapter;
import org.eclipse.birt.report.engine.api.script.instance.IDataSetInstance;

import edu.ucdavis.ss.lmsreports.domain.Activity;


public class LearningActivityCompletionHandler extends
		ScriptedDataSetEventAdapter {
	
	List<Activity> reportData;
	ResultSet resultset;
	int count = 0;
	int MAX_NUM;

	@Override
	public boolean fetch(IDataSetInstance dataSet, IUpdatableDataSetRow row)
			throws ScriptException {
try {
	    	
	    	
	    	
	        if (count < MAX_NUM)
	        {
	        	row.setColumnValue("ACTIVITYID", reportData.get(count).getActivityId());
	        	row.setColumnValue("ACTIVITYNAME", reportData.get(count).getActivityName());
	            row.setColumnValue("ORGNAME", reportData.get(count).getPrimaryDomain());
	            row.setColumnValue("ACTIVITYLABEL", reportData.get(count).getActivityType());
	            row.setColumnValue("CATEGORY", reportData.get(count).getCategory());
	            row.setColumnValue("ACTIVITYCODE", reportData.get(count).getActivityCd());
	            row.setColumnValue("STARTDT", reportData.get(count).getStartDt());
	            row.setColumnValue("ENDDT", reportData.get(count).getEndDt());
	            row.setColumnValue("REGISTERED", reportData.get(count).getRegisteredCount());
	            row.setColumnValue("ATTENDED", reportData.get(count).getAttendedCount());
	            row.setColumnValue("COMPLETED", reportData.get(count).getCompletedCount());
	            row.setColumnValue("COMPLETED_SINGLE", reportData.get(count).getCompletedSingleHeadcount());
	            
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

	@SuppressWarnings("unchecked")
	@Override
	public void beforeOpen(IDataSetInstance dataSet,
			IReportContext reportContext) throws ScriptException {
		// TODO Auto-generated method stub
		super.beforeOpen(dataSet, reportContext);
		
		reportData = (List<Activity>)reportContext.getParameterValue("resultSet");
		MAX_NUM = reportData.size();
	}

}
