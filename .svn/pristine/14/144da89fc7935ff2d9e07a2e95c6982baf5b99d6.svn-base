package edu.ucdavis.ss.lmsreports.data;

import java.util.List;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ucdavis.ss.lmsreports.domain.Activity;
import edu.ucdavis.ss.lmsreports.rowmapper.ActivityRowMapper;

@Repository("ActivityDao")
@Transactional
public class ActivityDaoImpl  implements
		ActivityDao {

	
   private static final int TIME_ZONE = 18;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	

	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Activity> getSelectedActivities(String domainId, String fromDate,
			String toDate, String activityType, String orgCd, String activityName, String courseCd, String limitedActivity) {
		
		StringBuilder sql= new StringBuilder();
		sql.append("WITH n( org_pk, org_name, org_cd) as  (SELECT org_pk, org_name, org_cd FROM org WHERE Org_PK = ");
		sql.append(domainId);
		sql.append(" UNION ALL SELECT  nplus1.org_pk, nplus1.org_name, nplus1.org_cd  ");
		sql.append(" FROM org as nplus1, n  ");
        sql.append(" WHERE n.org_pk = nplus1.org_prntorgfk) "); 
        sql.append(" SELECT  act.Activity_PK, act.ActivityName, act.code, n.org_cd, lbl.ActLabel_Name, ");
        sql.append(" dbo.ConvertUTCToLocalDate(act.StartDt, ");
        sql.append(TIME_ZONE);
        sql.append(") startDt, ");
        sql.append( "dbo.ConvertUTCToLocalDate(act.EndDt, ");
        sql.append(TIME_ZONE);
        sql.append(") endDt ");
        sql.append(" FROM tbl_tmx_activity act ");
        sql.append(" JOIN TBL_TMX_Activity root ON root.Activity_PK = act.RootActivityFK ");
        sql.append(" JOIN tbl_adm_domact dom ON dom.DomAct_ActFK = root.activity_pk");
        sql.append(" and dom.DomAct_PrmyInd = 1   ");
        sql.append(" JOIN n on dom.DomAct_DomainFK  = n.Org_PK ");
        sql.append(" JOIN actlabel lbl on act.ActivityLabelFK = lbl.ActLabel_PK");
        sql.append(" WHERE 1=1 ");
        if(fromDate != null && fromDate.length() > 0){
        	
        	sql.append(" AND act.startDt >=  convert( varchar(10), dbo.ConvertLocalDateToUTC('");
        	sql.append(fromDate);
        	sql.append(" ', ");
        	sql.append(TIME_ZONE);
        	sql.append(" ), 101)");
        	sql.append(" AND act.endDt -1 <=   convert( varchar(10),dbo.ConvertLocalDateToUTC('");
        	sql.append(toDate);
        	sql.append(" ', ");
        	sql.append(TIME_ZONE);
        	sql.append(" ) , 101)");  
        	
        
         
        	
        }
        
        if(limitedActivity != null && limitedActivity.length() >0){
        	sql.append(" and root.Activity_PK in (");
        	sql.append(limitedActivity);
        	sql.append(")");
        }
        
        if(activityType != null){
        	sql.append(" and lbl.ActLabel_PK in ( ");
        	sql.append(activityType);
        	sql.append(")");
        }
        
        
        if(activityName != null && activityName.trim().length() > 0){
        	sql.append(" and act.ActivityName like '%");
        	sql.append(activityName);
        	sql.append("%'");
        }
        
        if(courseCd != null && courseCd.trim().length() >0){
        	if(courseCd.contains(",")){
        		 String[] tokens = courseCd.split(",");
        		 StringBuilder str = new StringBuilder();
        		 for(int i=0; i<tokens.length; i++){
        			 str.append("'");
        			 str.append(tokens[i].trim());
        			 str.append("',");
        			 
        		 }
        		 str.deleteCharAt(str.length()-1);
        		
        		sql.append(" and act.Code in (");
        		sql.append(str.toString());
        		sql.append(")");
        	} else {
	        	sql.append(" and act.Code like '%");
	        	sql.append(courseCd);
	        	sql.append("%'");
        	}
        }
        
      //  if(orgCd != null && orgCd.trim().length() > 0){
      //  	sql.append(" and n.org_cd like '%");
      //  	sql.append(orgCd);
      //  	sql.append("%'");
      //  }
                
        sql.append(" ORDER BY act.activityName");
        
        
     
    	return jdbcTemplate.query(sql.toString(), new ActivityRowMapper());
	}

	@Override
	public List<Activity> getSelectedFutureActivities(String domainId, String fromDate,
			String toDate, String activityType, String orgCd, String activityName, String courseCd, String limitedActivities) {
		
		StringBuilder sql= new StringBuilder();
		sql.append("WITH n( org_pk, org_name, org_cd) as  (SELECT org_pk, org_name, org_cd FROM org WHERE Org_PK = ");
		sql.append(domainId);
		sql.append(" UNION ALL SELECT  nplus1.org_pk, nplus1.org_name, nplus1.org_cd  ");
		sql.append(" FROM org as nplus1, n  ");
        sql.append(" WHERE n.org_pk = nplus1.org_prntorgfk) "); 
        sql.append(" SELECT  act.Activity_PK, act.ActivityName, act.code, n.org_cd, lbl.ActLabel_Name, ");
        sql.append(" dbo.ConvertUTCToLocalDate(act.StartDt, ");
        sql.append(TIME_ZONE);
        sql.append(") startDt, ");
        sql.append( "dbo.ConvertUTCToLocalDate(act.EndDt, ");
        sql.append(TIME_ZONE);
        sql.append(") endDt ");
        sql.append(" FROM tbl_tmx_activity act ");
        sql.append(" JOIN TBL_TMX_Activity root ON root.Activity_PK = act.RootActivityFK ");
        sql.append(" JOIN tbl_adm_domact dom ON dom.DomAct_ActFK = root.activity_pk");
        sql.append(" and dom.DomAct_PrmyInd = 1   ");
        sql.append(" JOIN n on dom.DomAct_DomainFK  = n.Org_PK ");
        sql.append(" JOIN actlabel lbl on act.ActivityLabelFK = lbl.ActLabel_PK");
        sql.append(" WHERE act.StartDt >=  convert( varchar(10), dbo.ConvertLocalDateToUTC(CURRENT_TIMESTAMP, 18), 101) ");
        if(fromDate != null && fromDate.length() > 0){
        	
        	sql.append(" AND act.startDt >=  convert( varchar(10), dbo.ConvertLocalDateToUTC('");
        	sql.append(fromDate);
        	sql.append(" ', ");
        	sql.append(TIME_ZONE);
        	sql.append(" ), 101)");
        	sql.append(" AND act.endDt -1 <=   convert( varchar(10),dbo.ConvertLocalDateToUTC('");
        	sql.append(toDate);
        	sql.append(" ', ");
        	sql.append(TIME_ZONE);
        	sql.append(" ) , 101)");  
        	
        
         
        	
        }
        
        if(limitedActivities != null  && limitedActivities.length() >0)
        {
        	sql.append(" and root.Activity_PK in (");
        	sql.append(limitedActivities);
        	sql.append(")");
        }
        
        if(activityType != null){
        	sql.append(" and lbl.ActLabel_PK in ( ");
        	sql.append(activityType);
        	sql.append(")");
        }
        
        
        if(activityName != null && activityName.trim().length() > 0){
        	sql.append(" and act.ActivityName like '%");
        	sql.append(activityName);
        	sql.append("%'");
        }
        
        if(courseCd != null && courseCd.trim().length() >0){
        	if(courseCd.contains(",")){
       		 String[] tokens = courseCd.split(",");
       		 StringBuilder str = new StringBuilder();
       		 for(int i=0; i<tokens.length; i++){
       			 str.append("'");
       			 str.append(tokens[i].trim());
       			 str.append("',");
       			 
       		 }
       		 str.deleteCharAt(str.length()-1);
       		
       		sql.append(" and act.Code in (");
       		sql.append(str.toString());
       		sql.append(")");
       	} else {
	        	sql.append(" and act.Code like '%");
	        	sql.append(courseCd);
	        	sql.append("%'");
       	}
        }
        
        //if(orgCd != null && orgCd.trim().length() > 0){
       // 	sql.append(" and n.org_cd like '%");
       // 	sql.append(orgCd);
      //  	sql.append("%'");
       // }
                
        sql.append(" ORDER BY act.activityName");
        
        System.out.println(sql.toString());
     
    	return jdbcTemplate.query(sql.toString(), new ActivityRowMapper());
	}


	@Override
	public List<Activity> getSelectedRequiredActivities(String domainId) {
		
		StringBuilder sql= new StringBuilder();
		sql.append(" WITH rorg( org_pk, org_name, org_cd) as ");  
		sql.append(" (SELECT org_pk, org_name, org_cd  FROM org ");
		sql.append(" WHERE Org_PK =" );
		sql.append(domainId);
		sql.append(" UNION ALL SELECT  rorg1.org_pk, rorg1.org_name, rorg1.org_cd  ");
		sql.append(" FROM org as rorg1, rorg  ");
		sql.append(" WHERE rorg.org_pk = rorg1.org_prntorgfk) ");
		sql.append(" SELECT distinct act.Activity_PK,  act.ActivityName, act.code, NULL org_cd, null ActLabel_Name, ");
		sql.append(" dbo.ConvertUTCToLocalDate(act.startDt, ");
		sql.append(TIME_ZONE);
		sql.append(" ) startdt, ");
		sql.append(" dbo.ConvertUTCToLocalDate(act.enddt, ");
		sql.append(TIME_ZONE);
		sql.append(" ) enddt ");
		sql.append(" from  tbl_tmx_activity act "); 
		sql.append(" JOIN TBL_TMX_Activity root ON root.Activity_PK = act.RootActivityFK  ");
		//sql.append(" JOIN TBL_TMX_UserRequiredAct req on req.ActivityFK = act.Activity_PK  ");
		sql.append(" JOIN tbl_adm_domact dom ON dom.DomAct_ActFK = root.activity_pk and dom.DomAct_PrmyInd = 1 ");  
		sql.append(" JOIN rorg on dom.DomAct_DomainFK  = rorg.Org_PK  ");
		sql.append(" ORDER BY act.activityName");
		 System.out.println(sql.toString());
	     
	    return jdbcTemplate.query(sql.toString(), new ActivityRowMapper());
	}


	@Override
	public Activity getActivityById(String activityId) {
		
		StringBuilder sql= new StringBuilder();
		sql.append(" SELECT * FROM tbl_tmx_activity WHERE activity_pk = ? ");
		Activity activity = (Activity) jdbcTemplate.queryForObject(sql.toString(), new Object[]{activityId} , new ActivityRowMapper());
		return activity;
	}

	@Override
	public List<Activity> getActivitiesForPerson(String activityCd) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT null org_cd,  act.Activity_PK, act.ActivityName, act.code, lbl.ActLabel_Name, ");
        sql.append(" dbo.ConvertUTCToLocalDate(act.StartDt, ");
        sql.append(TIME_ZONE);
        sql.append(") startDt, ");
        sql.append( "dbo.ConvertUTCToLocalDate(act.EndDt, ");
        sql.append(TIME_ZONE);
        sql.append(") endDt ");
        sql.append(" FROM tbl_tmx_activity act ");
        sql.append(" JOIN TBL_TMX_Activity root ON root.Activity_PK = act.RootActivityFK ");
        sql.append(" JOIN actlabel lbl on act.ActivityLabelFK = lbl.ActLabel_PK");
        sql.append(" WHERE root.code in (");
        sql.append(activityCd);
        sql.append(")");
        return jdbcTemplate.query(sql.toString(), new ActivityRowMapper());
	}
	

	


	
}
