<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 <%@ include file="/WEB-INF/jsp/include/header.jsp" %>

 

<script type="text/javascript">
$(function(){	
	$("#selectedCampus").change(function(){
		  $("#reportType").val("changeCampus");
    	  $("form").attr("target", "_self");
		  $("#rosterParam").submit();

	     });	
	$(":button").click(function(){
		
		
		   if(!ValidateForm(this.form)){
			return;
		   }
		
	   
		if(this.id == "activities"){
			 $("#reportType").val("findActivities");
			 $("form").attr("target", "_self");
			this.form.submit();
			return;
		}
		
		
		 var act =$('input:radio[name=activityId]:checked').val();
	      //valid activity?
	        if(act == null || act.length== 0){
			alert("Please select an activity!");
			return;
	        }
		
	
			
		
		  	if(this.id == "excel"){
			   $("#reportType").val($(this).val());
			   $("form").attr("target", "_self");
			  this.form.submit();
			}
		    	
		    	
		   	 if(this.id =="report" || this.id=="pdf"){
		   		$("#reportType").val($(this).val());
			     $("form").attr("target", "_blank");
			     this.form.submit();
			 }

  	    
	});
	
});


	
</script>


<div>
	<ul class="xbreadcrumbs" id="breadcrumbs-1">
     <li>
        <a href="index.htm" class="home">Home</a>
     </li>
     <li>
     <a class="active">Roster Parameter Page</a>
      </li>
    </ul>
 </div>   

	<img class="center" id="loader" src="<%=request.getContextPath() %>/static/images/wait30.gif" style="display: none;" alt="logo"  />
	
  		
	<form:form id="rosterParam" method="post" modelAttribute="formBean" >
	
	
	
	<h2>Roster Report</h2>
	<h3>Please enter selection criteria:</h3>
	
	<p class="bold_red">* = required field</p>
	<p class="bold_red">** = wildcard or comma separated values</p>
	
	
   <c:set var="role" value= "${formBean.userRole}"/>

 <table class="param_table" cellspacing="0">
 
 <tr><td colspan = "4"> <form:errors path="*" cssClass="errorblock" element="div" /></td>	</tr>
  
         <tr>
        <td><form:label path="fromDate"><span class="bold_red">*</span>Session Date (From):</form:label></td>
      
        <td><form:input path="fromDate" class="date"/></td>
        <td><form:label path="toDate"><span class="bold_red">*</span>Session Date (To):</form:label></td>
        <td><form:input path="toDate" class="date" /></td>
    </tr>
     
    
       <c:if test="${role eq 'Admin' || role eq 'SuperUser'}">  
   <tr>
		
		<td><form:label path="selectedCampus"><span class="bold_red">*</span>Campus:</form:label></td>
		<td colspan= "3"><form:select path="selectedCampus" >
		   <form:options items="${formBean.locations}" itemLabel="locationName" itemValue="lmsKey" />
		</form:select>
		</td>
		
		
		
		</tr>
     </c:if>
    	
          <tr>
	       <td><form:label path="selectedDomain"><span class="bold_red">*</span>Domain:</form:label></td>
		   <td ><form:select path="selectedDomain">
		     <form:options items="${formBean.domains}" itemLabel="domainNamePadded" itemValue="domainId" />
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
      	 <form:label path="activityList">Please choose a domain, activity and the report output type below:</form:label>
         <table class="report_criteria">
         
         
    
      	<tr>
      	<td colspan="2"> <form:label path="activityList"><span class="bold_red">*</span>Activity List:</form:label></td>
      	</tr>
      	
      	
      <tr>
      <td colspan="2">
      <display:table id="activity" name="${formBean.activityList}" >
      <display:setProperty name="basic.msg.empty_list" value="${properties.nothingToDisplay}" />
      
       <display:column style="width:30px" >
         <form:radiobutton path="activityId" value="${activity.activityId}" />  
        </display:column> 
        
      <display:column title="Activity Name" property="activityName"/>
      <display:column title="Activity Code" property="activityCd"/>
      <display:column title="Start Date" format="{0,date,MM/dd/yyyy}" property="startDt"/>
      <display:column title="End Date" format="{0,date,MM/dd/yyyy}" property="endDt"/>
      </display:table>
      </td>
      </tr> 
      
      
       </table>
       
       
        <br>
		<form:label path="reportType">Report Output Options:</form:label>
		<br>
		
	 			
	 	<button  class="sexybutton" id="report" name="report" type="button" value="report"   > 
  		<span><span><span class="report">Report</span></span></span></button>
  		<button class="sexybutton"  id="pdf" name="pdf" type="button" value="pdf" >
  		<span><span><span class="report">PDF</span></span></span></button>
  		<button  class="sexybutton"  id="excel" name="excel" type="button" value="excel" >
  		<span><span><span class="report">Excel</span></span></span></button>
      </c:if>
           
  
  
      
  		
  		

  		  	
  		<form:hidden path="reportFile"  />
  		<form:hidden path="reportType" />
   
	</form:form>
	
	
<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>	
</body>
</html>