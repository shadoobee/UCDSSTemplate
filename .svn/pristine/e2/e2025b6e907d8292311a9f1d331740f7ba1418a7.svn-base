package edu.ucdavis.ss.lmsreports.birt.handler;

import java.util.List;

import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.engine.api.script.IUpdatableDataSetRow;
import org.eclipse.birt.report.engine.api.script.ScriptException;
import org.eclipse.birt.report.engine.api.script.eventadapter.ScriptedDataSetEventAdapter;
import org.eclipse.birt.report.engine.api.script.instance.IDataSetInstance;

import edu.ucdavis.ss.lmsreports.domain.RosterReport;

public class RosterSessionHandler extends ScriptedDataSetEventAdapter {
	List<RosterReport> reportData;
	int count = 0;
	int SESSION_SIZE;
	@Override
	public boolean fetch(IDataSetInstance dataSet, IUpdatableDataSetRow row)
			throws ScriptException {
		 try {
		    	
			   
		    	
		        if (count < SESSION_SIZE)
		        {
		            row.setColumnValue("StartTime", reportData.get(count).getSessionFromDate());
		            row.setColumnValue("EndTime", reportData.get(count).getSessionToDate());
		            row.setColumnValue("StartDate", reportData.get(count).getSessionFromDate());
		            row.setColumnValue("EndDate", reportData.get(count).getSessionToDate());
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
			super.beforeOpen(dataSet, reportContext);
			
			  reportData = (List<RosterReport>)reportContext.getParameterValue("sessionResultSet");
			  SESSION_SIZE = reportData.size();
	}
	

}
