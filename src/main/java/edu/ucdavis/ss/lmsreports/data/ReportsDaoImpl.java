package edu.ucdavis.ss.lmsreports.data;

import java.util.List;



import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.ucdavis.ss.lmsreports.domain.Activity;
import edu.ucdavis.ss.lmsreports.domain.Learner;
import edu.ucdavis.ss.lmsreports.domain.LearningActivityCompletionReport;
import edu.ucdavis.ss.lmsreports.domain.RosterReport;
import edu.ucdavis.ss.lmsreports.rowmapper.ComplianceByActivityReportRowMapper;
import edu.ucdavis.ss.lmsreports.rowmapper.ComplianceReportRowMapper;
import edu.ucdavis.ss.lmsreports.rowmapper.LearningActiivtyStatusRowMapper;
import edu.ucdavis.ss.lmsreports.rowmapper.LearningActivityDetailsRowMapper;
import edu.ucdavis.ss.lmsreports.rowmapper.LearningActivityRowMapper;
import edu.ucdavis.ss.lmsreports.rowmapper.RosterActivityRowMapper;
import edu.ucdavis.ss.lmsreports.rowmapper.RosterLearnerRowMapper;
import edu.ucdavis.ss.lmsreports.rowmapper.RosterSessionRowMapper;
import edu.ucdavis.ss.lmsreports.rowmapper.UpcomingActivitiesDetailsRowMapper;
import edu.ucdavis.ss.lmsreports.rowmapper.UpcomingActivitiesRowMapper;
import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;





public class ReportsDaoImpl implements ReportsDao {
    private static final int TIME_ZONE = 18;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	private static Logger LOG = Logger.getLogger(ReportsDao.class); 
	
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Activity> getLearningActivityStatusData(ParameterPageUi formBean) {
	
		StringBuilder sql= new StringBuilder();
		String userCd = formBean.getUserCd();
		String orgCd = formBean.getOrgCd();
		
		sql.append(" WITH rorg( org_pk, org_name, org_cd) as  (SELECT org_pk, org_name, org_cd  ");
		sql.append(" FROM org WHERE Org_PK = ");
		sql.append(formBean.getSelectedDomain());
		sql.append("  UNION ALL SELECT  rorg1.org_pk, rorg1.org_name, rorg1.org_cd ");
		sql.append(" FROM org as rorg1, rorg   WHERE rorg.org_pk = rorg1.org_prntorgfk) ");
		sql.append(" SELECT  act.Activity_PK, act.activityName, act.code, ");
		
		
		sql.append(" dbo.ConvertUTCToLocalDate(act.startDt, ");
		sql.append(TIME_ZONE);
		sql.append(") startDt , ");
		
		sql.append(" dbo.ConvertUTCToLocalDate(act.endDt, ");
		sql.append(TIME_ZONE);
		sql.append(") endDt , ");
		
		sql.append(" act.minCapacity, act.maxCapacity, ");
		sql.append(" case when act.active=1 then 'Active' else 'Cancelled' end activityStatus, org.Org_Name primaryOrg, lab.ActLabel_Name, ");
		sql.append(" SUM(CASE WHEN reg.status =0  THEN 1 ELSE 0 END ) registered_count,  ");
		sql.append(" SUM(CASE WHEN reg.status = 2 THEN 1 ELSE 0 END) waitlist_count,   ");
		sql.append(" SUM(CASE WHEN reg.status =1 THEN 1 ELSE 0 END) cancelled_count,  ");
		sql.append(" SUM(CASE WHEN reg.status =5 THEN 1 ELSE 0 END) noshow_count, ");
		sql.append(" SUM(CASE WHEN att.AttndStatusFK = 1 then 1 ELSE 0 END) attended_count,");
		sql.append(" count(expr.EmpFK) expressed_interest_count ");
		sql.append(" FROM  tbl_tmx_activity act    ");
		sql.append(" LEFT OUTER JOIN  TBL_TMX_ActMetaData m on m.ActivityFK = act.Activity_PK ");  
		sql.append(" LEFT OUTER JOIN ConType ct on ct.ConType_PK = m.ConTypeFK  ");  
		sql.append(" JOIN TBL_TMX_attempt att on att.ActivityFK = act.Activity_PK ");
		sql.append(" join tbl_tmx_registration reg on reg.Reg_PK = att.AuthRegFK  ");
		sql.append(" JOIN tblemp emp on emp.Emp_PK = att.EmpFK   ");
		sql.append(" JOIN empcd on empcd.EmpCd_PK = emp.Emp_EmpCdFK  ");
		sql.append(" JOIN tblEmpOrg eo on eo.EmpOrg_EmpFK = emp.Emp_PK  and eo.EmpOrg_PrmyInd = 1  ");
		sql.append(" JOIN rorg on rorg.Org_PK = eo.EmpOrg_OrgFK   ");
		sql.append(" left outer JOIN TBL_TMX_ExpressInterest expr on expr.ActivityFK = act.activity_Pk and expr.EmpFK = emp.Emp_PK ");
		sql.append(" LEFT OUTER JOIN tbl_adm_domact dom ON dom.DomAct_ActFK = act.activity_pk and dom.DomAct_PrmyInd = 1 ");
		sql.append(" LEFT OUTER JOIN org on dom.DomAct_DomainFK  = org.Org_PK ");
		sql.append(" JOIN actlabel lab on lab.ActLabel_PK= act.activityLabelFK ");
		sql.append(" where (act.Activity_PK in (");
		sql.append(formBean.getActivityId());
		sql.append(")");
		sql.append("or act.RootActivityFK in (");
		sql.append(formBean.getActivityId());
		sql.append("))");
				
		if(userCd != null && userCd.trim().length() > 0){
			sql.append(" AND empcd.EmpCd_Name like '%");
			sql.append(userCd);
			sql.append("%'");
		}
		
		
		if(orgCd != null && orgCd.trim().length() >0){
        	if(orgCd.contains(",")){
       		 String[] tokens = orgCd.split(",");
       		 StringBuilder str = new StringBuilder();
       		 for(int i=0; i<tokens.length; i++){
       			 str.append("'");
       			 str.append(tokens[i].trim());
       			 str.append("',");
       			 
       		 }
       		 str.deleteCharAt(str.length()-1);
       		
       		sql.append(" AND rorg.org_cd in (");
       		sql.append(str.toString());
       		sql.append(")");
       	} else {
	        	sql.append(" AND rorg.org_cd like '%");
	        	sql.append(orgCd.trim());
	        	sql.append("%'");
       	}
        }
			
		
		
		sql.append(" group by act.activity_pk,act.activityName, act.code, act.startDt, act.endDt , ");
		sql.append(" act.minCapacity, act.maxCapacity, act.active, org.org_Name, lab.ActLabel_Name ");
		   LOG.info("the sql " + sql.toString());
			return jdbcTemplate.query(sql.toString(), new LearningActiivtyStatusRowMapper());
	}

	@Override
	public List<Learner> getReportData(String domain,  String activityId, String userCd, String orgCd) {

		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" WITH rorg( org_pk, org_name, org_cd) as  (SELECT org_pk, org_name, org_cd  ");
		sql.append(" FROM org WHERE Org_PK = ");
		sql.append(domain);
		sql.append("  UNION ALL SELECT  rorg1.org_pk, rorg1.org_name, rorg1.org_cd ");
		sql.append(" FROM org as rorg1, rorg   WHERE rorg.org_pk = rorg1.org_prntorgfk) ");
		sql.append(" SELECT distinct rorg.Org_Name primaryorganization,  act.Code, emp.emp_cntry,  emp.emp_lname  + ', ' +  emp.emp_fname  learner,  ");
		sql.append(" CASE WHEN emp.Emp_EMail like '%noemail%' then ' ' else emp.emp_EMail end learner_email, ");  
		sql.append(" es.EmpStat_Name status, manager.emp_lname + ', ' + manager.emp_fname manager,  act.ActivityName, " );
        sql.append(" CASE WHEN empcd.empcd_name = '~' THEN ' ' else empcd.empcd_name END user_cd, "); 
		sql.append(" convert(varchar(10), dbo.ConvertUTCToLocalDate(ura.DueDate, ");
		sql.append(TIME_ZONE);
		sql.append("), 101) DueDate, ");
		sql.append("convert(varchar(10), dbo.ConvertUTCToLocalDate(att.EndDt, ");
		sql.append(TIME_ZONE);
		sql.append(" ) ,101) completiondate,  ");
		sql.append("convert(varchar(10), dbo.ConvertUTCToLocalDate(ura.ExpirationDate, ");
		sql.append(TIME_ZONE);
		sql.append(" ), 101) ExpirationDate, " );
		sql.append(" case when ura.DueDate >ura.ExpirationDate then ");
		sql.append(" convert(varchar(10), dbo.ConvertUTCToLocalDate(ura.DueDate, ");
		sql.append(TIME_ZONE);
		sql.append(" ), 101) else ");
		sql.append(" convert(varchar(10), dbo.ConvertUTCToLocalDate(ura.ExpirationDate, ");
		sql.append(TIME_ZONE);
		sql.append("  ), 101)  end training_due_date,  ");
		sql.append(" case when ura.ReqStatus = 0 then 'Assigned'  ");
		sql.append(" when ura.reqStatus = 1 then 'In Progress' ");
		sql.append(" when ura.reqstatus = 2 then 'Satisfied'  ");
		sql.append(" when ura.reqstatus = 3 then 'Overdue'  ");
		sql.append(" when ura.reqstatus = 4 then 'Expired' end cert_status, job_name  ");
		sql.append(" FROM tbl_tmx_activity act  ");
		sql.append(" LEFT outer join tbl_tmx_attempt att on att.ActivityFK = act.Activity_PK  ");
		sql.append(" JOIN tblemp emp on emp.Emp_PK = att.EmpFK  ");
		sql.append(" JOIN EmpStat es on es.EmpStat_PK = emp.Emp_EmpStatFK ");
		sql.append(" LEFT outer JOIN TBL_TMX_UserRequiredAct  ura on ura.EmpFK = emp.Emp_PK   and act.Activity_PK = ura.ActivityFK  ");
		sql.append(" LEFT OUTER JOIN tblemp manager on  emp.Emp_MgrEmpFK = manager.Emp_PK   ");
		sql.append(" JOIN emporg eo ON emp.Emp_PK = eo.EmpOrg_EmpFK AND eo.EmpOrg_PrmyInd = 'true'  ");
		sql.append(" JOIN tblempjob empjob on empjob.EmpJob_EmpFK = emp.Emp_PK and empjob.EmpJob_PrmyInd = 'true'   ");
		sql.append(" jOIN job on job.Job_PK = empjob.EmpJob_JobFK  "); 
		sql.append(" JOIN rorg   ON eo.EmpOrg_OrgFK = rorg.org_pk ");    
		sql.append(" JOIN empcd on empcd_pk = emp.emp_empcdfk ");
		sql.append(" where (act.Activity_PK in (");
		sql.append(activityId);
		sql.append(")");
		sql.append("or act.RootActivityFK in (");
		sql.append(activityId);
		sql.append("))");
		  if (userCd != null && userCd.trim().length()>0){
	        	sql.append( "and empcd.empcd_name like '%");
	        	sql.append(userCd);
	        	sql.append("%'");
	        }
	        
		  if(orgCd != null && orgCd.trim().length() >0){
	        	if(orgCd.contains(",")){
	       		 String[] tokens = orgCd.split(",");
	       		 StringBuilder str = new StringBuilder();
	       		 for(int i=0; i<tokens.length; i++){
	       			 str.append("'");
	       			 str.append(tokens[i].trim());
	       			 str.append("',");
	       			 
	       		 }
	       		 str.deleteCharAt(str.length()-1);
	       		
	       		sql.append(" AND rorg.org_cd in (");
	       		sql.append(str.toString());
	       		sql.append(")");
	       	} else {
		        	sql.append(" AND rorg.org_cd like '%");
		        	sql.append(orgCd.trim());
		        	sql.append("%'");
	       	}
	        }
				
				
		
			
        LOG.info("the sql " + sql.toString());
		return jdbcTemplate.query(sql.toString(), new ComplianceByActivityReportRowMapper());
	}

	@Override
	public List<Learner> getComplianceByLearnerReportData(String domain,
			String activityType, String activity, String statusType, String attended,
			String fromDate, String toDate, String orgCd, String userCd, String activityCd) {
		
		StringBuilder sql= new StringBuilder();
		
		
		
		
		
		
		
		sql.append(" WITH rorg( org_pk, org_name, org_cd) as  (SELECT org_pk, org_name, org_cd  ");
		sql.append(" FROM org WHERE Org_PK = ");
		sql.append(domain);
		sql.append("  UNION ALL SELECT  rorg1.org_pk, rorg1.org_name, rorg1.org_cd ");
		sql.append(" FROM org as rorg1, rorg   WHERE rorg.org_pk = rorg1.org_prntorgfk) ");
		sql.append(" SELECT act.Activity_PK,  emp.emp_lname  + ', ' +  emp.emp_fname  learner,  ");
		sql.append(" emp.emp_cntry, ");
		sql.append(" rorg.Org_Name homedepartment,  rorg.org_cd, ");
		sql.append(" act.ActivityName, dbo.ConvertUTCToLocalDate(uract.DueDate, ");
		sql.append(TIME_ZONE);
		sql.append(" ) DueDate,    dbo.ConvertUTCToLocalDate(uract.ExpirationDate, ");
		sql.append(TIME_ZONE);
		sql.append(" ) expiration_date, uract.ReqStatus, ");
		sql.append(" CASE WHEN uract.ReqStatus = 0 then 'PLANNED'   WHEN uract.ReqStatus = 1 THEN 'IN PROGRESS'  WHEN uract.ReqStatus = 2 THEN 'CURRENT'  ");
		sql.append(" WHEN uract.ReqStatus = 3 THEN 'OVERDUE'  WHEN uract.ReqStatus= 4 THEN 'EXPIRED' END certstatus, dbo.ConvertUTCToLocalDate(att.EndDt, ");
		sql.append(TIME_ZONE);
		sql.append(" ) cert_award_dt,");
		sql.append(" dbo.ConvertUTCToLocalDate ");
		sql.append(" (case when uract.DueDate is not null and uract.ExpirationDate is not null and uract.DueDate > uract.ExpirationDate  then uract.DueDate ");
		sql.append("  when uract.duedate is not null and uract.ExpirationDate is not null and uract.ExpirationDate > uract.DueDate then uract.ExpirationDate ");
		sql.append("  when uract.DueDate is not null and uract.ExpirationDate is null then uract.DueDate");
		sql.append("  when uract.ExpirationDate is not null and uract.DueDate is null then uract.ExpirationDate  end ,");
		sql.append(TIME_ZONE);
		sql.append(" ) training_due_date,  "); 
        sql.append(" case when emp.Emp_EMail like '%noemail%' then ' ' else emp.Emp_EMail end emp_email,");
        sql.append(" rorg.org_name, org.org_PK, ");
        sql.append(" case when empcd.EmpCd_name = '~' then ' ' else empcd.EmpCd_name end  userCd  ");
		sql.append(" FROM  tblemp emp ");  
		sql.append(" LEFT OUTER JOIN TBL_TMX_UserRequiredAct  uract on uract.EmpFK = emp.Emp_PK  ");
		sql.append(" JOIN tbl_tmx_activity act on act.Activity_PK = uract.ActivityFK   ");
		sql.append(" JOIN TBL_TMX_Activity root ON root.Activity_PK = act.RootActivityFK  ");
		sql.append(" JOIN tbl_adm_domact dom ON dom.DomAct_ActFK = root.activity_pk and dom.DomAct_PrmyInd = 1  ");
		sql.append(" JOIN org on dom.DomAct_DomainFK  = org.Org_PK   ");
		sql.append(" JOIN empstat on empstat.EmpStat_PK = emp.Emp_EmpStatFK   ");
		sql.append(" LEFT OUTER JOIN tblemp manager on  emp.Emp_MgrEmpFK = manager.Emp_PK ");  
		sql.append(" JOIN emporg eo ON (emp.Emp_PK = eo.EmpOrg_EmpFK AND eo.EmpOrg_PrmyInd = 'true') ");   
		sql.append(" JOIN tblempjob empjob on empjob.EmpJob_EmpFK = emp.Emp_PK and empjob.EmpJob_PrmyInd = 'true' "); 
		sql.append(" JOIN job on job.Job_PK = empjob.EmpJob_JobFK ");   
		sql.append(" JOIN rorg  ON eo.EmpOrg_OrgFK = rorg.Org_PK ");
		sql.append(" JOIN EmpCd on empcd.EmpCd_PK = emp.Emp_EmpCdFK ");  
		sql.append(" LEFT OUTER JOIN tbl_tmx_attempt att ON att.activityfk = act.activity_PK  and att.EmpFK = emp.Emp_PK ");  
		sql.append(" WHERE 1 =1  ");
		
		
		
				
		if(activityType != null && activityType.trim().length() >0 ){
			sql.append(" AND act.ActivityLabelFK in ( ");
			sql.append(activityType);
			sql.append(")");
		}
		if(statusType != null && statusType.trim().length() > 0){
			sql.append(" AND uract.ReqStatus in ( ");
			sql.append(statusType);
			sql.append(")");
		}
		
		if(attended != null && attended.trim().length() > 0){
			sql.append(" AND att.AttndStatusFK = 1 ");
		}
		
		if(activity != null && activity.length() > 0){
			sql.append(" AND act.ActivityName like '%");
			sql.append(activity);
			sql.append("%'");
		}
		
		if(fromDate !=null &&  fromDate.length() > 0){
			
			sql.append(" AND uract.DueDate >=  convert( varchar(10), dbo.ConvertLocalDateToUTC('");
			sql.append(fromDate);
			sql.append("', ");
			sql.append(TIME_ZONE);
			sql.append("), 101)");
            sql.append(" AND uract.DueDate -1 <=   convert( varchar(10),dbo.ConvertLocalDateToUTC('");
            sql.append(toDate);
            sql.append(" ', ");
            sql.append(TIME_ZONE);
            sql.append(") , 101) ");
		}
		
		 if(orgCd != null && orgCd.trim().length() >0){
	        	if(orgCd.contains(",")){
	       		 String[] tokens = orgCd.split(",");
	       		 StringBuilder str = new StringBuilder();
	       		 for(int i=0; i<tokens.length; i++){
	       			 str.append("'");
	       			 str.append(tokens[i].trim());
	       			 str.append("',");
	       			 
	       		 }
	       		 str.deleteCharAt(str.length()-1);
	       		
	       		sql.append(" AND rorg.org_cd in (");
	       		sql.append(str.toString());
	       		sql.append(")");
	       	} else {
		        	sql.append(" AND rorg.org_cd like '%");
		        	sql.append(orgCd.trim());
		        	sql.append("%'");
	       	}
	        }
		
		if(userCd != null && userCd.trim().length() >0){
			sql.append(" AND empCd.empCd_Name like '%");
			sql.append(userCd);
			sql.append("%'");
		}
		
		if(activityCd != null && activityCd.trim().length() >0){
			sql.append(" AND act.code in (");
			sql.append(activityCd);
			sql.append(")");
		}
		LOG.info("the sql " + sql.toString());
		return jdbcTemplate.query(sql.toString(), new ComplianceReportRowMapper());
	}

	@Override
	public List<RosterReport> getRosterActivityReportData(String activityId) {
		
		
		StringBuilder sql= new StringBuilder();
		
		
		sql.append(" SELECT	count(reg.EmpFK) total_registered,	");
		sql.append(" inst.Inst_FName + ' ' + inst.Inst_LName  instructor_name,  ");
		sql.append(" act.Code,  act.ActivityName,  ");
		sql.append(" dbo.ConvertUTCToLocalDate(act.StartDt, ");
		sql.append(TIME_ZONE);
		sql.append(" ) StartDt, ");
		sql.append(" dbo.ConvertUTCToLocalDate(act.EndDt, ");
		sql.append(TIME_ZONE);
		sql.append(" ) EndDt,");
		sql.append(" act.ActivityDesc, Loc.Loc_Name,");	 
		sql.append(" fac.Fac_Name, fac.Fac_Add1, fac.Fac_Add2, fac.Fac_City, fac.Fac_State, fac.Fac_Zip, ");
		sql.append("  CASE when loc.Loc_MaxCap != null then loc.loc_maxCap else act.maxCapacity end max_cap	"); 
		sql.append(" FROM tbl_tmx_activity act	"); 
		sql.append(" JOIN TBL_TMX_Activity root ON root.Activity_PK = act.RootActivityFK  ");
		sql.append(" LEFT OUTER JOIN tbl_tmx_actinst instxref on instxref.ActivityFK = act.Activity_PK	and instxref.PrimaryInd = 0  ");
		sql.append(" LEFT OUTER JOIN tbl_tmx_actloc aloc on aloc.ActivityFK = act.Activity_PK  ");
		sql.append(" LEFT OUTER JOIN loc on loc.Loc_PK = aloc.LocFK  ");
		sql.append(" LEFT OUTER JOIN fac on fac.Fac_PK = loc.Loc_FacFK ");
		sql.append(" LEFT OUTER JOIN tbl_tmx_registration reg on reg.ActivityFK = act.Activity_PK  ");
		sql.append(" LEFT OUTER JOIN inst on inst.Inst_PK = instxref.InstFK	and inst.Active = 1  ");
		sql.append(" WHERE act.Activity_PK = ");
		sql.append(activityId); 
		sql.append(" group by inst.Inst_FName,  inst.Inst_LName,  act.Code,	 act.ActivityName,	 act.StartDt,  ");
		sql.append("  act.EndDt,	 act.ActivityDesc,	 Loc.Loc_Name,	");
		sql.append("fac.Fac_Name, fac.Fac_Add1, fac.Fac_Add2, fac.Fac_City, fac.Fac_State, fac.Fac_Zip, loc.Loc_MaxCap	,act.maxCapacity ");

		
		
		LOG.info("the sql " + sql.toString());
		return jdbcTemplate.query(sql.toString(), new RosterActivityRowMapper());
	}

	@Override
	public List<RosterReport> getRosterReportData(String activityId) {
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" SELECT	");
		sql.append(" emp.emp_lname + ', ' + emp.emp_fname     learner_name, ");																
		sql.append(" primaryorg.Org_Cd,	");																	
		sql.append(" primaryorg.Org_Name home_department, ");
		sql.append(" emp.emp_cntry, ");
		sql.append(" emp.emp_email user_email, ");																
		sql.append(" case when emp.Emp_Phn1 = '~' then ' ' else emp.Emp_Phn1 end phone	");																	
		sql.append(" FROM tbl_tmx_activity act ");																	
		sql.append(" JOIN TBL_TMX_Activity root ON root.Activity_PK = act.RootActivityFK ");																		
		//sql.append(" JOIN tbl_adm_domact dom ON dom.DomAct_ActFK = root.activity_pk	and dom.DomAct_PrmyInd = 1 ");																	
		//sql.append(" JOIN org org ON dom.DomAct_DomainFK  = org.Org_PK	");																	
		//sql.append(" JOIN tbl_tmx_actloc aloc on aloc.ActivityFK = act.Activity_PK	");																	
		//sql.append(" JOIN loc on loc.Loc_PK = aloc.LocFK ");																		
		sql.append(" JOIN tbl_tmx_registration reg on  reg.ActivityFK = act.Activity_PK ");
		sql.append(" JOIN tblEmp emp ON emp.emp_pk = reg.EmpFK ");		
		//sql.append(" JOIN tblempjob empjob ON (empjob.EmpJob_EmpFK = emp.Emp_PK AND empjob.EmpJob_PrmyInd = 'true')");
		//sql.append(" JOIN job ON job.Job_PK = empjob.EmpJob_JobFK ");							
		sql.append(" LEFT OUTER JOIN emporg eo ON (emp.Emp_PK = eo.EmpOrg_EmpFK AND eo.EmpOrg_PrmyInd = 'true')");	
		sql.append(" LEFT OUTER JOIN org primaryorg ON eo.EmpOrg_OrgFK = primaryorg.Org_PK	");						
		sql.append(" WHERE act.Activity_PK = ");
		sql.append(activityId);
		sql.append(" ORDER BY LEARNER_NAME ");
		LOG.info("the sql " + sql.toString());
		return jdbcTemplate.query(sql.toString(), new RosterLearnerRowMapper());
	}

	

	@Override
	public List<RosterReport> getRelatedSessionData(String activityId) {
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select  ");
		sql.append(" dbo.ConvertUTCToLocalDate(startdt,");
		sql.append(TIME_ZONE);
		sql.append(" ) startdt, ");
		sql.append(" dbo.ConvertUTCToLocalDate(enddt, ");
		sql.append(TIME_ZONE);
		sql.append(" ) enddt ");
		sql.append(" from tbl_tmx_activity where prntactfk in ");
        sql.append(" (select prntactfk from tbl_tmx_activity where activity_pk =");
        sql.append(activityId);
        sql.append(" ) or activity_pk = (select RootActivityFK from tbl_tmx_activity where activity_pk = ");
        sql.append(activityId);
        sql.append(" ) order by enddt ");
        LOG.info("the sql " + sql.toString());
		return jdbcTemplate.query(sql.toString(), new RosterSessionRowMapper());
	}

	@Override
	public List<Activity>getUpcomingActivityReportData(ParameterPageUi formBean) {
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" WITH rorg( org_pk, org_name, org_cd) as  (SELECT org_pk, org_name, org_cd  ");
		sql.append(" FROM org WHERE Org_PK = ");
		sql.append(formBean.getSelectedDomain());
		sql.append("  UNION ALL SELECT  rorg1.org_pk, rorg1.org_name, rorg1.org_cd ");
		sql.append(" FROM org as rorg1, rorg   WHERE rorg.org_pk = rorg1.org_prntorgfk) ");
	    sql.append(" SELECT act.ActivityName,  act.Activity_PK,  act.Code, lab.ActLabel_Name, ");
		sql.append(" org.Org_Name, dbo.ConvertUTCToLocalDate(act.EndDt, ");
		sql.append(TIME_ZONE);
		sql.append(" ) EndDt, "); 
		sql.append(" dbo.ConvertUTCToLocalDate(act.StartDt, ");
		sql.append(TIME_ZONE);
		sql.append(" ) StartDt, ");
		sql.append(" sum(case when reg.status = 0 then 1 else 0 end) registered_count, ");
		sql.append(" sum(case when reg.status = 1 then 1 else 0 end) cancelled_count "); 
		sql.append(" FROM tbl_tmx_activity act "); 
		sql.append(" join ActLabel lab on lab.ActLabel_PK = act.ActivityLabelFK ");  
		sql.append(" join TBL_TMX_Registration reg on reg.ActivityFK = act.Activity_PK ");
		sql.append(" left outer join tbl_adm_domact da on da.DomAct_ActFK = act.Activity_PK and da.DomAct_PrmyInd = 1  ");
		sql.append(" left outer join org on da.DomAct_DomainFK = org.Org_PK  ");
		sql.append(" jOIN TBL_TMX_attempt att on att.ActivityFK = act.Activity_PK   and att.authregfk = reg.reg_pk   ");
		sql.append(" JOIN tblemp emp on emp.Emp_PK = att.EmpFK "); 
		sql.append(" JOIN empCd on empCd.EmpCd_PK = emp.Emp_EmpCdFK  ");
		sql.append(" JOIN tblEmpOrg eo on eo.EmpOrg_EmpFK = emp.Emp_PK  ");
		sql.append(" JOIN rorg  on rorg.Org_PK = eo.EmpOrg_OrgFK  ");
		
		sql.append(" where (act.Activity_PK in (");
		sql.append(formBean.getActivityId());
		sql.append(")");
		sql.append("or act.RootActivityFK in (");
		sql.append(formBean.getActivityId());
		sql.append("))");
		
		if(formBean.getUserCd() != null && formBean.getUserCd().trim().length() > 0){
			sql.append(" AND empCd.empCd_Name like '%");
			sql.append(formBean.getUserCd());
			sql.append("%'");
		}
		
		 if(formBean.getOrgCd() != null && formBean.getOrgCd().trim().length() >0){
	        	if(formBean.getOrgCd().contains(",")){
	       		 String[] tokens = formBean.getOrgCd().split(",");
	       		 StringBuilder str = new StringBuilder();
	       		 for(int i=0; i<tokens.length; i++){
	       			 str.append("'");
	       			 str.append(tokens[i].trim());
	       			 str.append("',");
	       			 
	       		 }
	       		 str.deleteCharAt(str.length()-1);
	       		
	       		sql.append(" AND rorg.org_cd in (");
	       		sql.append(str.toString());
	       		sql.append(")");
	       	} else {
		        	sql.append(" AND rorg.org_cd like '%");
		        	sql.append(formBean.getOrgCd().trim());
		        	sql.append("%'");
	       	}
	        }
		sql.append(" group by  act.ActivityName,  act.Code, lab.ActLabel_Name,act.EndDt, act.StartDt, org.org_Name, act.activity_pk ");

		LOG.info("the sql " + sql.toString());
		return jdbcTemplate.query(sql.toString(), new UpcomingActivitiesRowMapper());

		
	}

	@Override
	public List<Learner> getUpcomingActivityDetails(String activityId , String userCd, String orgCd) {
		
		StringBuilder sql= new StringBuilder();
		
		
		 sql.append(" WITH  primaryorg (org_pk , top_name)   as (SELECT org_pk ,org_name as top_name  FROM org WHERE  Org_PrntOrgFK = 2  ");  
		 sql.append(" UNION ALL SELECT chd.org_pk ,par.top_name   FROM primaryorg  par    JOIN org chd ON chd.org_prntorgfk = par.org_pk ) ");
		 sql.append(" SELECT act.ActivityName,  act.code,  emp.emp_lname  + ', ' +  emp.emp_fname  learner,  empstat.EmpStat_Name status,  org.Org_Name homedepartment,");   
		 sql.append(" primaryorg.top_name primaryorganization,    manager.emp_lname + ', ' + manager.emp_fname manager, ");
		 sql.append(" org.org_cd learner_org_cd,   emp.emp_no learner_usernumber,  ");  
		 sql.append(" emp.Emp_Cntry learner_employee_number,   job.Job_Name,  att.attndstatusfk, ");
		 sql.append(" CASE WHEN empcd.EmpCd_Name = '~' THEN ' ' ELSE empcd.EmpCd_Name END user_cd, ");
		 sql.append(" CASE WHEN reg.status = 0  OR att.attndstatusfk IS NULL THEN 'Registered'   WHEN reg.status= 1 or att.attndstatusfk = 2  ");
		 sql.append(" THEN 'Cancelled' END reg_status, null learner_email, null DueDate, null CompletionDate, null ExpirationDate, null cert_status ");
		 sql.append(" FROM tblemp emp  ");
		 sql.append(" JOIN tbl_tmx_registration reg ON reg.EmpFK = emp.Emp_PK  ");
		 sql.append(" JOIN tbl_tmx_activity act ON act.Activity_PK = reg.ActivityFK  ");
		 sql.append(" jOIN TBL_TMX_attempt att ON att.ActivityFK = act.Activity_PK  AND  emp.Emp_PK = att.EmpFK  ");   
		 sql.append(" AND att.authregfk = reg.reg_pk ");
		 sql.append(" JOIN empstat ON empstat.EmpStat_PK = emp.Emp_EmpStatFK  ");  
		 sql.append(" LEFT OUTER JOIN tblemp manager ON  emp.Emp_MgrEmpFK = manager.Emp_PK  "); 
		 sql.append(" left outer JOIN emporg eo ON (emp.Emp_PK = eo.EmpOrg_EmpFK AND eo.EmpOrg_PrmyInd = 'true')    ");
		 sql.append(" JOIN tblempjob empjob ON empjob.EmpJob_EmpFK = emp.Emp_PK  and empjob.EmpJob_PrmyInd = 'true' ");  
		 sql.append(" JOIN job ON job.Job_PK = empjob.EmpJob_JobFK  ");
		 sql.append(" left outer JOIN org  ON eo.EmpOrg_OrgFK =org.org_pk  ");
		 sql.append(" left outer JOIN primaryorg ON eo.EmpOrg_OrgFK = primaryorg.org_pk   "); 
		 sql.append(" join EmpCd on empcd.EmpCd_PK = emp.Emp_EmpCdFK");
		 sql.append(" WHERE act.Activity_PK= ");
	 	 sql.append(activityId);
	 	 sql.append(" AND (reg.status in (0,1) OR att.attndstatusfk IN (null, 2)) ");
	 	 
	 	 if (userCd != null && userCd.trim().length() >0  && !userCd.equalsIgnoreCase("null")){
	 		 sql.append(" AND empCd.empCd_Name like '%");
	 		 sql.append(userCd);
	 		 sql.append("%'");
	 	 }
	 	 
	 	 if(orgCd != null && orgCd.trim().length() >0 && ! orgCd.equalsIgnoreCase("null")){
	 		 sql.append(" AND org.org_cd like '%");
	 		 sql.append(orgCd);
	 		 sql.append("%'");
	 	 }

        
	 	LOG.info("the sql " + sql.toString());
		return jdbcTemplate.query(sql.toString(), new UpcomingActivitiesDetailsRowMapper());
	}

	@Override
	public List<Activity> getLearningActivityCompletionReportData(
			String activities, String fromDate, String toDate, String domain,
			String userCd, String orgCd) {
	
		StringBuilder sql= new StringBuilder();
		
		sql.append(" WITH rorg( org_pk, org_name, org_cd) as  (SELECT org_pk, org_name, org_cd  ");
		sql.append(" FROM org WHERE Org_PK = ");
		sql.append(domain);
		sql.append("  UNION ALL SELECT  rorg1.org_pk, rorg1.org_name, rorg1.org_cd ");
		sql.append(" FROM org as rorg1, rorg   WHERE rorg.org_pk = rorg1.org_prntorgfk) ");
		sql.append(" SELECT act.activity_pk, act.activityName, ");
		sql.append("  act.startdt, ");
		sql.append("  act.enddt, ");
		sql.append("  act.code, ");
		sql.append("  ct.ConType_Name, ");
		sql.append("  lab.actlabel_name, ");
		sql.append("  org.org_name, ");
		sql.append("  headcount.registered_count, ");
		sql.append("  headcount.attended_count, ");
		sql.append("  headcount.completed_count, ");
		sql.append("  single_headcount.completed_single_headcount ");
		sql.append("  FROM ");
		sql.append(" (SELECT  act.Activity_PK, ");
		sql.append(" SUM(CASE WHEN att.attndstatusfk is null THEN 1 ELSE 0 END ) registered_count, ");
		sql.append(" SUM(CASE WHEN att.AttndStatusFK = 1 THEN 1 ELSE 0 END) attended_count,  "); 
		sql.append(" SUM(CASE WHEN att.completionstatus = 1 THEN 1 ELSE 0 END) completed_count  ");
		sql.append(" FROM  tbl_tmx_activity act   ");
		sql.append(" LEFT OUTER JOIN  TBL_TMX_ActMetaData m on m.ActivityFK = act.Activity_PK  "); 
		sql.append(" LEFT OUTER JOIN ConType ct on ct.ConType_PK = m.ConTypeFK   ");
		sql.append(" JOIN TBL_TMX_attempt att on att.ActivityFK = act.Activity_PK  ");
		sql.append(" JOIN tblemp emp on emp.Emp_PK = att.EmpFK  ");
		sql.append(" JOIN empcd on empcd.EmpCd_PK = emp.Emp_EmpCdFK ");  
		sql.append(" JOIN tblEmpOrg eo on eo.EmpOrg_EmpFK = emp.Emp_PK  and eo.EmpOrg_PrmyInd = 1 ");
		sql.append(" JOIN rorg on rorg.Org_PK = eo.EmpOrg_OrgFK  "); 
		sql.append(" WHERE (act.activity_pk in ( ");
		sql.append(activities);
		sql.append(")");
		sql.append(" or act.RootActivityFK in ( ");
		sql.append(activities);
		sql.append("))");
		if(userCd != null && userCd.trim().length() >0){
	    	sql.append(" AND empCd.empCd_Name like '%");
	    	sql.append(userCd);
	    	sql.append("%'");
	    }
	    
		if(orgCd != null && orgCd.trim().length() >0){
        	if(orgCd.contains(",")){
       		 String[] tokens = orgCd.split(",");
       		 StringBuilder str = new StringBuilder();
       		 for(int i=0; i<tokens.length; i++){
       			 str.append("'");
       			 str.append(tokens[i].trim());
       			 str.append("',");
       			 
       		 }
       		 str.deleteCharAt(str.length()-1);
       		
       		sql.append(" AND rorg.org_cd in (");
       		sql.append(str.toString());
       		sql.append(")");
       	} else {
	        	sql.append(" AND rorg.org_cd like '%");
	        	sql.append(orgCd.trim());
	        	sql.append("%'");
       	}
        }
		
	    sql.append("  group by activity_pk) headcount");
		sql.append(" LEFT OUTER JOIN ");
		sql.append(" (SELECT act.Activity_PK, ");
		sql.append(" COUNT(DISTINCT att.EmpFK) completed_single_headcount   ");
		sql.append(" FROM  tbl_tmx_activity act   ");
		sql.append(" JOIN TBL_TMX_attempt att on att.ActivityFK = act.Activity_PK  ");
		sql.append(" JOIN tblemp emp on emp.Emp_PK = att.EmpFK  ");
		sql.append(" JOIN empcd on empcd.EmpCd_PK = emp.Emp_EmpCdFK "); 
		sql.append(" JOIN tblEmpOrg eo on eo.EmpOrg_EmpFK = emp.Emp_PK  and eo.EmpOrg_PrmyInd = 1 ");
		sql.append(" JOIN rorg on rorg.Org_PK = eo.EmpOrg_OrgFK  "); 
		sql.append(" WHERE ( act.activity_pk in ( ");
		sql.append(activities);
		sql.append(")");
		sql.append(" or act.RootActivityFK in ( ");
		sql.append(activities);
		sql.append("))");
		if(userCd != null && userCd.trim().length() >0){
	    	sql.append(" AND empCd.empCd_Name like '%");
	    	sql.append(userCd);
	    	sql.append("%'");
	    }
	    
	    if(orgCd != null && orgCd.trim().length() >0){
	    	sql.append(" AND rorg.org_cd like  '%");
	    	sql.append(orgCd.trim());
	    	sql.append("%'");
	    }
		
		sql.append(" AND att.CompletionStatus = 1");
		sql.append(" group by act.Activity_PK) single_headcount ");
		sql.append(" ON headcount.activity_pk = single_headcount.activity_pk ");
		sql.append(" join tbl_tmx_activity act on act.activity_pk = headcount.activity_pk ");
		sql.append(" LEFT OUTER JOIN  TBL_TMX_ActMetaData m on m.ActivityFK = act.Activity_PK ");
		sql.append(" LEFT OUTER JOIN ConType ct on ct.ConType_PK = m.ConTypeFK   ");
		sql.append(" JOIN actlabel lab on lab.actlabel_pk = act.ActivityLabelFK  ");
		sql.append(" LEFT OUTER JOIN tbl_adm_domact da on da.DomAct_ActFK = act.Activity_PK and da.DomAct_PrmyInd = 1  ");
		sql.append(" LEFT OUTER JOIN org on org.Org_PK = da.DomAct_DomainFK  ");
		
		
		
		
	    LOG.info("the sql " + sql.toString());
		return jdbcTemplate.query(sql.toString(), new LearningActivityRowMapper());
	}

	@Override
	public List<Learner> getLearningActivityDetails(String activityId, String domainId, String userCd, String orgCd) {
	
		StringBuilder sql= new StringBuilder();
		
		
		sql.append(" WITH rorg( org_pk, org_name, org_cd) as  (SELECT org_pk, org_name, org_cd  ");
		sql.append(" FROM org WHERE Org_PK = ");
		sql.append(domainId);
		sql.append("  UNION ALL SELECT  rorg1.org_pk, rorg1.org_name, rorg1.org_cd ");
		sql.append(" FROM org as rorg1, rorg   WHERE rorg.org_pk = rorg1.org_prntorgfk) ");
		sql.append(" ,  primaryorg (org_pk , top_name)   as  (SELECT org_pk ,org_name as top_name ");
		sql.append(" FROM org WHERE  Org_PrntOrgFK = 2    UNION ALL SELECT chd.org_pk ,par.top_name  ");
		sql.append(" FROM primaryorg  par    JOIN org chd ON chd.org_prntorgfk = par.org_pk ) ");
		sql.append(" SELECT act.ActivityName,  emp.emp_lname  + ', ' +  emp.emp_fname  learner,  ");
		sql.append(" empstat.EmpStat_Name status,  rorg.Org_Name homedepartment,  ");
		sql.append(" case when empcd.EmpCd_Name  = '~' then ' ' else empcd.EmpCd_Name end user_cd, ");
		sql.append(" primaryorg.top_name org_name,    manager.emp_lname + ', ' + manager.emp_fname manager, ");
		sql.append(" manager.Emp_EMail manager_email,    ");
		sql.append(" rorg.org_cd learner_org_cd,  ");
		sql.append(" case when emp.Emp_EMail like '%noemail%' then ' ' else emp.Emp_EMail end learner_email,  emp.emp_no learner_usernumber, ");
		sql.append(" emp.Emp_Cntry learner_employee_number,  job.Job_Name , ");
		sql.append(" CONVERT(VARCHAR(10), dbo.ConvertUTCToLocalDate(att.EndDt, ");
		sql.append(TIME_ZONE);
		sql.append(" ), 101) EndDt,");  
		sql.append(" case when att.CompletionStatus = 1 then 'Completed'");
		sql.append(" when att.AttndStatusFK = 1 then 'Attended' ");
		sql.append(" when att.AttndStatusFK is null then 'Registered' end completion_status ");
		sql.append(" FROM tbl_tmx_activity act  ");
		sql.append(" jOIN TBL_TMX_attempt att on att.ActivityFK = act.Activity_PK ");
		sql.append(" JOIN tblemp emp on emp.Emp_PK = att.EmpFK ");
		sql.append(" JOIN empstat on empstat.EmpStat_PK = emp.Emp_EmpStatFK  "); 
		sql.append(" LEFT OUTER JOIN tblemp manager on  emp.Emp_MgrEmpFK = manager.Emp_PK ");  
		sql.append(" LEFT OUTER JOIN emporg eo ON (emp.Emp_PK = eo.EmpOrg_EmpFK AND eo.EmpOrg_PrmyInd = 'true')  ");  
		sql.append(" LEFT OUTER JOIN tblempjob empjob on empjob.EmpJob_EmpFK = emp.Emp_PK and empjob.EmpJob_PrmyInd = 'true' ");
		sql.append(" LEFT OUTER JOIN job on job.Job_PK = empjob.EmpJob_JobFK  ");   
		sql.append(" JOIN rorg  ON eo.EmpOrg_OrgFK=rorg.org_pk    ");
		sql.append(" JOIN primaryorg on eo.EmpOrg_OrgFK = primaryorg.org_pk    ");
		sql.append(" join EmpCd on empcd.EmpCd_PK = emp.Emp_EmpCdFK ");
		sql.append(" WHERE (att.AttndStatusFK not in (2,3,4,5,6)  or att.attndstatusfk is null) ");
        sql.append(" and act.Activity_PK = ");
        sql.append(activityId);
		
		
		if(userCd != null && userCd.trim().length() > 0){
			sql.append(" AND empcd.EmpCd_Name like '%");
			sql.append(userCd);
			sql.append("%'");
		}
			
		if(orgCd != null && orgCd.trim().length() >0){
			sql.append(" AND rorg.org_cd like '%");
			sql.append(orgCd.trim());
			sql.append("%'");
		}
	
		
		LOG.info("the sql " + sql.toString());
		return jdbcTemplate.query(sql.toString(), new LearningActivityDetailsRowMapper());
	}

	
	@Override
	public List<LearningActivityCompletionReport> getLearningActivityReportData(String activityId, String fromDate, String toDate, String domain, String userCd, String orgCd) {
	
		StringBuilder sql= new StringBuilder();
		
		
		sql.append(" WITH  primaryorg (org_pk , top_name)  "); 
		sql.append(" as (SELECT org_pk ,org_name as top_name FROM org WHERE  Org_PrntOrgFK = 2  ");
		sql.append(" UNION ALL SELECT chd.org_pk ,par.top_name  FROM primaryorg  par   JOIN org chd ON chd.org_prntorgfk = par.org_pk ) ");
		sql.append(" ,rorg( org_pk, org_name, org_cd) as  ");
		sql.append(" (SELECT org_pk, org_name, org_cd  FROM org WHERE Org_PK = ");
		sql.append(domain);
		sql.append(" UNION ALL SELECT  rorg1.org_pk, rorg1.org_name, rorg1.org_cd ");
		sql.append(" FROM org as rorg1, rorg   WHERE rorg.org_pk = rorg1.org_prntorgfk) ");
		sql.append(" SELECT  act.ActivityName,  emp.emp_lname  + ', ' +  emp.emp_fname  learner,");  
		sql.append(" empstat.EmpStat_Name status,  rorg.Org_Name homedepartment,  primaryorg.top_name org_name,   ");
		sql.append(" manager.emp_lname + ', ' + manager.emp_fname manager,  manager.Emp_EMail manager_email,  ");
		sql.append(" rorg.org_cd learner_org_cd,  emp.Emp_EMail learner_email,  emp.emp_no learner_usernumber,   ");
		sql.append(" emp.Emp_Cntry learner_employee_number,   job.Job_Name ,  dbo.ConvertUTCToLocalDate(att.EndDt, 18 ) EndDt ");
		sql.append(" FROM tblemp emp  ");
		sql.append(" JOIN tbl_tmx_registration reg on reg.EmpFK = emp.Emp_PK   ");
		sql.append(" JOIN tbl_tmx_activity act on act.Activity_PK = reg.ActivityFK  ");
		sql.append(" JOIN TBL_TMX_Activity root ON root.Activity_PK = act.RootActivityFK  ");
		sql.append(" JOIN TBL_TMX_attempt att on att.ActivityFK = act.Activity_PK  and  emp.Emp_PK = att.EmpFK  ");
		sql.append(" JOIN empstat on empstat.EmpStat_PK = emp.Emp_EmpStatFK  ");
		sql.append(" LEFT OUTER JOIN tblemp manager on  emp.Emp_MgrEmpFK = manager.Emp_PK "); 
		sql.append(" LEFT OUTER JOIN emporg eo ON (emp.Emp_PK = eo.EmpOrg_EmpFK AND eo.EmpOrg_PrmyInd = 'true')  ");
		sql.append(" LEFT OUTER JOIN tblempjob empjob on empjob.EmpJob_EmpFK = emp.Emp_PK and empjob.EmpJob_PrmyInd = 'true'   ");
		sql.append(" LEFT OUTER JOIN job on job.Job_PK = empjob.EmpJob_JobFK   ");
		sql.append(" JOIN rorg  ON eo.EmpOrg_OrgFK =rorg.org_pk  ");
		sql.append(" JOIN primaryorg on eo.EmpOrg_OrgFK = primaryorg.org_pk  ");
		sql.append(" JOIN EmpCd on EmpCd.EmpCd_PK = emp.Emp_EmpCdFK" );
		sql.append(" WHERE  reg.LstUpd = (select max(lstupd) from TBL_TMX_Registration a   ");
		sql.append(" WHERE a.EmpFK = emp.Emp_PK and a.ActivityFK= act.Activity_PK)  ");
		sql.append(" AND act.Activity_PK in ( ");
		sql.append(activityId); 
		sql.append(") and reg.status = 4 ");
		
		if(fromDate !=null &&  fromDate.length() > 0){
			sql.append(" AND att.enddt >=  convert( varchar(10), dbo.ConvertLocalDateToUTC('");
			sql.append(fromDate);
			sql.append("', ");
			sql.append(TIME_ZONE);
			sql.append("), 101)");
            sql.append(" AND att.enddt -1 <=   convert( varchar(10),dbo.ConvertLocalDateToUTC('");
            sql.append(toDate);
            sql.append(" ', ");
            sql.append(TIME_ZONE);
            sql.append(") , 101) ");
            
		 
		}
		
		
		if(userCd != null && userCd.trim().length()>0){
			sql.append(" AND EmpCd.EmpCd_Name like '%");
			sql.append(userCd);
			sql.append("%'");
			
		}
	
		if(orgCd != null && orgCd.trim().length()>0){
			sql.append(" and rorg.org_cd like '%");
			sql.append(orgCd.trim());
			sql.append("%'");
		}
		
		LOG.info("the sql " + sql.toString());
	
		return null;
	}
	
	
	
	}

