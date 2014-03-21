package edu.ucdavis.ss.lmsreports.birt.handler;

import java.sql.ResultSet;

import java.util.List;

import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.engine.api.script.IUpdatableDataSetRow;
import org.eclipse.birt.report.engine.api.script.ScriptException;
import org.eclipse.birt.report.engine.api.script.eventadapter.ScriptedDataSetEventAdapter;
import org.eclipse.birt.report.engine.api.script.instance.IDataSetInstance;

import edu.ucdavis.ss.lmsreports.domain.Activity;

public class UpcomingActivitiesHandler extends ScriptedDataSetEventAdapter {
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
		            row.setColumnValue("learningActivity", reportData.get(count).getActivityName());
		            row.setColumnValue("primaryDomain", reportData.get(count).getPrimaryDomain());
		            row.setColumnValue("activityCode", reportData.get(count).getActivityCd());
		            row.setColumnValue("activityType", reportData.get(count).getActivityType());
		            row.setColumnValue("activityStartDt", reportData.get(count).getStartDt());
		            row.setColumnValue("activityEndDt", reportData.get(count).getEndDt());
		            row.setColumnValue("cancelledCount", reportData.get(count).getCancelledCount());
		            row.setColumnValue("registeredCount", reportData.get(count).getRegisteredCount());
		            row.setColumnValue("activityId", reportData.get(count).getActivityId());
		           
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
