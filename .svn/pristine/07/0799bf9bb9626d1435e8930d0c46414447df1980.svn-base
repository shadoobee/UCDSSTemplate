package edu.ucdavis.ss.lmsreports.data;

import java.util.List;





import edu.ucdavis.ss.lmsreports.domain.Activity;

public interface ActivityDao {
	List<Activity> getSelectedActivities(String domainId, String fromDate, String toDate, String activityType, String orgCd, String actName, String courseCd, String limitedActivites);
	List<Activity> getSelectedFutureActivities(String domainId, String fromDate, String toDate, String activityType, String orgCd, String actName, String courseCd, String limitedActivities);
	List<Activity> getSelectedRequiredActivities(String domainId);
	Activity getActivityById(String activityId);
	List<Activity> getActivitiesForPerson(String activityId);

}
