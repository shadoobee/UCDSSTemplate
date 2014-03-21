package edu.ucdavis.ss.lmsreports.data;

import java.util.List;


import javax.sql.DataSource;
import edu.ucdavis.ss.lmsreports.domain.Reports;

public interface ReportListDao  {
	
	List<Reports> getAll();
 
}
