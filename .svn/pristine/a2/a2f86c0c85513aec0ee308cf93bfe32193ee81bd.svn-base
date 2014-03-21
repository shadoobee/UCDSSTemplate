package edu.ucdavis.ss.lmsreports.birt.handler;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.engine.api.script.IUpdatableDataSetRow;
import org.eclipse.birt.report.engine.api.script.ScriptException;
import org.eclipse.birt.report.engine.api.script.eventadapter.ScriptedDataSetEventAdapter;
import org.eclipse.birt.report.engine.api.script.instance.IDataSetInstance;

import edu.ucdavis.ss.lmsreports.domain.Activity;


public class LearningActivityStatusHandler extends ScriptedDataSetEventAdapter {
	List<Activity> reportData;
	ResultSet resultset;
	int count = 0;
	int MAX_NUM;
	@Override
	public boolean fetch(IDataSetInstance dataSet, IUpdatableDataSetRow row)
			throws ScriptException {
		String startDate = null;
		String endDate = null;
		try {
	    	
	    	
	    	
	    	
	        if (count < MAX_NUM)
	        {
	        	
	        	SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy h:mm aaa");
	        	if(reportData.get(count).getStartDt() != null ){
	        		 startDate= fmt.format(reportData.get(count).getStartDt());
	        	}
	        	
	        	if(reportData.get(count).getEndDt() != null ){
	        		endDate= fmt.format(reportData.get(count).getEndDt());
	        	}
	        	
	            row.setColumnValue("ACTIVITYNAME", reportData.get(count).getActivityName());
	            row.setColumnValue("PRIMARYDOMAIN", reportData.get(count).getPrimaryDomain());
	            row.setColumnValue("ACTIVITYCODE", reportData.get(count).getActivityCd());
	            row.setColumnValue("ACTIVITYLABEL", reportData.get(count).getActivityType());
	            row.setColumnValue("STARTDATE", startDate);
	            row.setColumnValue("ENDDATE", endDate);
	            row.setColumnValue("ACTIVITYSTATUS", reportData.get(count).getStatus());
	            row.setColumnValue("REGISTEREDCOUNT", reportData.get(count).getRegisteredCount());
	            row.setColumnValue("WAITLISTCOUNT", reportData.get(count).getWaitListCount());
	            row.setColumnValue("CANCELLEDCOUNT", reportData.get(count).getCancelledCount());
	            row.setColumnValue("NOSHOWCOUNT", reportData.get(count).getNoShowCount());
	            row.setColumnValue("EXPRESSEDINTERESTCOUNT", reportData.get(count).getExpressedInterestCount());
	            row.setColumnValue("MINCAPACITY", reportData.get(count).getMinCapacity());
	            row.setColumnValue("MAXCAPACITY", reportData.get(count).getMaxCapacity());
	            row.setColumnValue("ATTENDEDCOUNT", reportData.get(count).getAttendedCount());
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

		reportData = (List<Activity>)reportContext.getParameterValue("resultSet");
		MAX_NUM = reportData.size();
	}

}
