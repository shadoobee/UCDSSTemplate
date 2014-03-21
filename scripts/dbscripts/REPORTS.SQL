USE LMSREPORTING

GO

CREATE TABLE REPORTS  ( 
	REPORT_ID  	INT NOT NULL,
	REPORT_NAME	VARCHAR(50) NOT NULL,
	REPORT_DESC	VARCHAR(255) NOT NULL,
	REPORT_FILE	VARCHAR(50) NOT NULL,
	PAGE       	VARCHAR(255) NOT NULL 
	)
GO
INSERT INTO REPORTS(REPORT_ID, REPORT_NAME, REPORT_DESC, REPORT_FILE, PAGE)
  VALUES(1, 'Compliance by Learner', 'View a list of learners who have assigned activities and see the status of their certificates.', 'ComplianceByLearner.rptdesign', 'complianceByLearner.htm')
GO
INSERT INTO REPORTS(REPORT_ID, REPORT_NAME, REPORT_DESC, REPORT_FILE, PAGE)
  VALUES(2, 'Compliance by Activity', 'Report containing learner information for a given activity', 'ComplianceByActivity.rptdesign', 'complianceByActivity.htm')
GO
INSERT INTO REPORTS(REPORT_ID, REPORT_NAME, REPORT_DESC, REPORT_FILE, PAGE)
  VALUES(3, 'Roster', 'Get a roster for a selected activity', 'Roster.rptdesign', 'roster.htm')
GO
INSERT INTO REPORTS(REPORT_ID, REPORT_NAME, REPORT_DESC, REPORT_FILE, PAGE)
  VALUES(4, 'Learning Activity Completions', 'This report shows Registration, Attended, Completed (Single and Duplicated) counts for selected activities.
Click on Activity name to view roster.', 'LearningActivity.rptdesign', 'learningActivityCompletions.htm')
GO

INSERT INTO REPORTS(REPORT_ID, REPORT_NAME, REPORT_DESC, REPORT_FILE, PAGE)
  VALUES(5, 'Upcoming Activiites', 'Get a list of activities with registration and cancellation counts.', 'UpcomingActivities.rptdesign', 'upcomingActivites.htm')
GO


INSERT INTO REPORTS(REPORT_ID, REPORT_NAME, REPORT_DESC, REPORT_FILE, PAGE)
  VALUES(6, 'Learning Activity Status', 'This report shows selected activities and its registration, wait list, cancellation, no show, and expressed interest counts.', 'LearningActivityStatus.rptdesign', 'learningActivityStatus.htm')
GO
