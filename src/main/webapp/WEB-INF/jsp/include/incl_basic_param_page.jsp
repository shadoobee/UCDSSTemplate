	<h3>Please enter selection criteria:</h3>
	<p class="bold_red">* = required field</p>
	<p class="bold_red">** = wildcard or comma separated values</p>
	
 <table class="param_table" cellspacing="0"  >
 <tr><td colspan = "4"> <form:errors path="*" cssClass="errorblock" element="div" /></td>	</tr>
  
         <tr>
        <td><form:label path="fromDate">Session Date (From):</form:label></td>
         <td><form:input path="fromDate" class="date"/></td>
        <td><form:label path="toDate">Session Date (To):</form:label></td>
        <td><form:input path="toDate" class="date" /></td>
    </tr>
         
        <c:set var="role" value= "${formBean.userRole}"/>
        
       <c:if test="${role eq 'Admin' || role eq 'SuperUser'}"> 
   <tr>
		  <td><form:label path="selectedCampus">Campus:</form:label></td>
		<td colspan= "3"><form:select path="selectedCampus" >
		   <form:options items="${formBean.locations}" itemLabel="locationName" itemValue="lmsKey" />
		</form:select>
		</td>
		
		
		
		</tr>
</c:if>

     <tr>
	       <td> <form:label path="selectedDomain">Domain:</form:label></td>
		   <td><form:select path="selectedDomain">
		   <form:options items="${formBean.domains}" itemLabel="domainNamePadded" itemValue="domainId" />
		  </form:select>
		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	   </tr> 
      
      	
          	  <tr>
	      <td><form:label path="activityType">Activity Type:</form:label></td>
	        <td><form:select path="activityType" multiple="multiple">
	        <form:options items="${formBean.activityTypes}" itemLabel="activityTypeDesc" itemValue="ActivityTypeId" />
	      
		</form:select>
		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
        </tr>
         
        
         <tr>
	       <td> <form:label path="activityName">Activity Name:</form:label></td>
		   <td><form:input path="activityName"/></td>
		   <td>&nbsp;</td>
           <td>&nbsp;</td>
		   </tr>
		 
		   <tr>
	       <td> <form:label path="courseCode"><span class="bold_red">**</span>Activity Code:</form:label></td>
	      
		   <td><form:input path="courseCode"/></td>
		   <td>&nbsp;</td>
           <td>&nbsp;</td>
           
		   </tr>
		 
        
        <tr><td colspan = "4" style="text-align:center;"> <button id="activities" name="activities" type="button" value="findActivities"  > Find Activities</button></td>
   
      </tr> 
      
      
       </table>
      
      
    
      	<c:if test="${formBean.activityList != null }" >
      	<br>
      	
  
      	 <form:label path="activityList">Please choose an activity, other optional criteria,  and the report output type below:</form:label>
      	 
      	
            <table class="report_criteria">
      	 <tr><td>
      	 <table class="main_table" >
	       <tr>
	        <td><form:label path="orgCd"><span class="bold_red">**</span>Organization Code:</form:label></td>
            <td><form:input path="orgCd" /></td>
           </tr>
	    
 		 <tr>
	      <td><form:label path="userCd">User Code:</form:label></td>
          <td><form:input path="userCd" /></td>
         </tr>
         </table>
         </td></tr>
       
	   
      	<tr><td > <form:label path="activityList"><span class="bold_red">*</span>Activity List:</form:label>
      	</td>
      	 </tr>
      
      <tr>     
      <td >
            <display:table id="activity" name="${formBean.activityList}"	 >
        <display:setProperty name="basic.msg.empty_list" value="${properties.nothingToDisplay}" />
      <display:column title="<input type='checkbox' id='selectall' />">
     
       <form:checkbox path="activityId" value="${activity.activityId}" />  
    
       </display:column> 
      <display:column title="Activity Name"  property="activityName"/>
      <display:column title="Activity Code" property="activityCd"/>
     <display:column title="Activity Type " property="category"/>
      <display:column title="Start Date" property="startDt"  format="{0,date,MM/dd/yyyy}"/>
      <display:column title="End Date" property="endDt"  format="{0,date,MM/dd/yyyy}"/>
      </display:table>
      </td>
      </tr> 
      
      </table>
            
         <br>
		<form:label path="reportType">Report Output Options:</form:label>
		<br>
	 	<button  class="sexybutton" id="report" name="report" type="button" value="report"  > 
  		<span><span><span class="report">Report</span></span></span></button>
  		<button class="sexybutton"  id="pdf" name="pdf" type="button" value="pdf" >
  		<span><span><span class="report">PDF</span></span></span></button>
  		<button  class="sexybutton"  id="excel" name="excel" type="button" value="excel" >
  		<span><span><span class="report">Excel</span></span></span></button>
  		
      </c:if>
        
 
  
            <br>
 
  		
 
  		  	   <form:hidden path="reportFile"  />
  		  	   <form:hidden path="organization" />
  		       <form:hidden path="reportType" />