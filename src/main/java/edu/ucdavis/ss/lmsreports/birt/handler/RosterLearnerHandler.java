package edu.ucdavis.ss.lmsreports.birt.handler;

import java.util.List;
import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.engine.api.script.IUpdatableDataSetRow;
import org.eclipse.birt.report.engine.api.script.ScriptException;
import org.eclipse.birt.report.engine.api.script.eventadapter.ScriptedDataSetEventAdapter;
import org.eclipse.birt.report.engine.api.script.instance.IDataSetInstance;

import edu.ucdavis.ss.lmsreports.domain.RosterReport;

public class RosterLearnerHandler extends ScriptedDataSetEventAdapter {
	List<RosterReport> reportData;
	int count = 0;
	int LEARNER_SIZE;
	@Override
	public boolean fetch(IDataSetInstance dataSet, IUpdatableDataSetRow row)
			throws ScriptException {
		 try {
		    	
			   
		    	
		        if (count < LEARNER_SIZE)
		        {
		        	row.setColumnValue("Learner", reportData.get(count).getLearner());
		            //row.setColumnValue("OrgCd", reportData.get(count).getOrgCd());
		            row.setColumnValue("HomeDepartment", reportData.get(count).getHomeDept());
		            //row.setColumnValue("PrimaryJob", reportData.get(count).getJob());
		            row.setColumnValue("Email",reportData.get(count).getEmail());
		            row.setColumnValue("Phone", reportData.get(count).getPhone());
		            row.setColumnValue("EmployeeID", reportData.get(count).getEmployeeID());
		         
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
		  reportData = (List<RosterReport>)reportContext.getParameterValue("learnerResultSet");
		 
		LEARNER_SIZE = reportData.size();
	}
	

}
