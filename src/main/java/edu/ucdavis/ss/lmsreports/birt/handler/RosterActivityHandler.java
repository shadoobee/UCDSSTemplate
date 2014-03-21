package edu.ucdavis.ss.lmsreports.birt.handler;




import java.util.List;

import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.engine.api.script.IUpdatableDataSetRow;
import org.eclipse.birt.report.engine.api.script.ScriptException;
import org.eclipse.birt.report.engine.api.script.eventadapter.ScriptedDataSetEventAdapter;
import org.eclipse.birt.report.engine.api.script.instance.IDataSetInstance;

import edu.ucdavis.ss.lmsreports.domain.RosterReport;

public class RosterActivityHandler extends ScriptedDataSetEventAdapter {
	List<RosterReport> activityReportData;
	int count = 0;
	int ACTIVITY_SIZE;
	StringBuilder instList = new StringBuilder();


	@Override
	public boolean fetch(IDataSetInstance dataSet, IUpdatableDataSetRow row)
			throws ScriptException {
		 try {
		    	
		   
		    	
		        if (count < ACTIVITY_SIZE)
		        {  
		        
		     
		        	
		        	
		        	row.setColumnValue("Instructor", instList.toString());
		            row.setColumnValue("FromDate", activityReportData.get(count).getSessionFromDate());
		            row.setColumnValue("ToDate", activityReportData.get(count).getSessionToDate());
		            
		              row.setColumnValue("Location", activityReportData.get(count).getLocation());
		            row.setColumnValue("ActivityName", activityReportData.get(count).getActivityName());
		            row.setColumnValue("ActivityCode", activityReportData.get(count).getActivityCd());
		            row.setColumnValue("MaxCapacity", activityReportData.get(count).getMaxCapacity());
		            row.setColumnValue("TotalRegistered", activityReportData.get(count).getTotalRegistered());
		            row.setColumnValue("ActivityDesc", activityReportData.get(count).getActivityDesc());
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
		
		activityReportData = (List<RosterReport>)reportContext.getParameterValue("activityResultSet");
	
		ACTIVITY_SIZE = activityReportData.size();
	
		for(RosterReport rr : activityReportData){
			instList.append(rr.getInstructor());
			instList.append(", ");
		}
		if(instList.length() >= 2){
		  instList.setLength(instList.length()-2);
		}
	
	}
	

}
