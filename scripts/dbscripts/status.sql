USE LMSREPORTING

GO


CREATE TABLE STATUS  ( 
	STATUS_ID    	INT NOT NULL,
	STATUS_DESC  	VARCHAR(50) NOT NULL,
	LMS_STATUS_CD	INT NOT NULL,
	REPORT_ID    	INT NOT NULL 
	)
GO
INSERT INTO STATUS(STATUS_ID, STATUS_DESC, LMS_STATUS_CD, REPORT_ID)
  VALUES(1, 'Current', 2, 1)
GO
INSERT INTO STATUS(STATUS_ID, STATUS_DESC, LMS_STATUS_CD, REPORT_ID)
  VALUES(2, 'Overdue', 3, 1)
GO
INSERT INTO STATUS(STATUS_ID, STATUS_DESC, LMS_STATUS_CD, REPORT_ID)
  VALUES(3, 'Expired', 4, 1)
GO
INSERT INTO STATUS(STATUS_ID, STATUS_DESC, LMS_STATUS_CD, REPORT_ID)
  VALUES(4, 'Planned', 0, 1)
GO
INSERT INTO STATUS(STATUS_ID, STATUS_DESC, LMS_STATUS_CD, REPORT_ID)
  VALUES(5, 'In Progress', 1, 1)
GO
