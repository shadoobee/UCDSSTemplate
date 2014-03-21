create view DBO.BANNER_COURSE_COMPLETIONS
as
select act.Activity_PK, act.ActivityName, act.Code, att.Attempt_PK, 
SumTotalLMSMirror.dbo.ConvertUTCToLocalDate(att.EndDt, 18)as  CompletionDate,
CASE WHEN att.CompletionStatus = 1 THEN 'COMPLETED' ELSE 'NOTCOMPLETED' END as CompletionStatus, 
emp.Emp_LName as LastName, 
emp.Emp_FName as FirstName, 
emp.Emp_Cntry as EmployeeID, 
emp.Emp_No as EmpNo
from SumTotalLMSMirror.dbo.TBL_TMX_Activity act
join SumTotalLMSMirror.dbo.tbl_tmx_attempt att on att.ActivityFK = act.Activity_PK
join SumTotalLMSMirror.dbo.tblemp emp on emp.Emp_PK = att.EmpFK
where act.RootActivityFK in (136078, 144993)