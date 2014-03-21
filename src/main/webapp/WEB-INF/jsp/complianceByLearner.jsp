

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>


 <script type="text/javascript">
			
 $(function(){	
	 $("#activityType").multiselectdropdown({ selectedItemsText: '*', noneSelectedText: "Select Activity Type "});
	 $("#selectedStatusType").multiselectdropdown({ selectedItemsText: '*', noneSelectedText: "Select Status Type "});
	    $("#selectedCampus").change(function(){
	      $("#reportType").val("changeCampus");
	      $("form").attr("target", "_self");	
		  $("#complianceParamLearner").submit();

	     });
						
			$(":button").click(function(){
				
		
			if(!ValidateForm(this.form)){
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
     <a class="active">Compliance by Learner Report Parameter Page</a>
      </li>
    </ul>
</div>	


	<img class="center" id="loader" src="<%=request.getContextPath() %>/static/images/wait30.gif" style="display: none;" alt="logo"  />

	<form:form id="complianceParamLearner" method="post" modelAttribute="formBean" >
	
	
	
	<h2>Compliance by Learner Report</h2>
	<h3>Please enter selection criteria:</h3>
	<p class="bold_red">* = required field</p>
	<p class="bold_red">** = wildcard or comma separated values</p>
 <table class="param_table" cellspacing="0">
 <tr><td colspan = "4"> <form:errors path="*" cssClass="errorblock" element="div" /></td>	</tr>
         <tr>
        <td><form:label path="fromDate"><span class="bold_red">*</span>Due Date (From):</form:label></td>
      
        <td><form:input path="fromDate" class="date"/></td>
        <td><form:label path="toDate"><span class="bold_red">*</span>Due Date (To):</form:label></td>
        <td><form:input path="toDate" class="date" /></td>
    </tr>
 
       <c:set var="role" value= "${formBean.userRole}"/>
      
       <c:if test="${role eq 'Admin' || role eq 'SuperUser'}"> 
   <tr>
		<td><form:label path="selectedCampus"><span class="bold_red">*</span>Campus:</form:label></td>
		<td><form:select path="selectedCampus" >
		   <form:options items="${formBean.locations}" itemLabel="locationName" itemValue="lmsKey" />
		</form:select>
		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		
		</tr>
</c:if>

     <tr>
	    <td><form:label path="selectedDomain">Domains:</form:label></td>
		<td><form:select path="selectedDomain">
		   <form:options items="${formBean.domains}" itemLabel="domainNamePadded" itemValue="domainId" />
		</form:select>
		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	  </tr>
      
          <tr>
	        <td><form:label path="activityType" >Activity Type:</form:label></td>
	        <td><form:select path="activityType"  multiple="multiple" >
	         <form:options items="${formBean.activityTypes}" itemLabel="activityTypeDesc" itemValue="ActivityTypeId" />
		</form:select>
		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
        </tr>
        
         <tr>
	     <td><form:label path="orgCd"><span class="bold_red">**</span>Organization Code:</form:label></td>
        <td><form:input path="orgCd" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
     
	    </tr>
	     
	    
 <tr>
	     <td><form:label path="userCd">User Code:</form:label></td>
        <td><form:input path="userCd" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
     
	    </tr>
        
         
        <tr>
	     <td><form:label path="selectedStatusType">Status Type:</form:label></td>
         <td><form:select path="selectedStatusType"  multiple="multiple" >
             <form:options items="${formBean.statusType}" itemLabel="statusDesc" itemValue="statusCd" />
		</form:select>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
     
	    </tr>
	   
	
        
   </table>
        <br>
       
  
		
	 		
	 	<button  class="sexybutton" id="report" name="report" type="button" value="report"   > 
  		<span><span><span class="report">Report</span></span></span></button>
  		<button class="sexybutton"  id="pdf" name="pdf" type="button" value="pdf" >
  		<span><span><span class="report">PDF</span></span></span></button>
  		<button  class="sexybutton"  id="excel" name="excel" type="button" value="excel" >
  		<span><span><span class="report">Excel</span></span></span></button>
  		
    		     
  		      
  		  
  		      
  		      
  		       <form:hidden path="reportFile"  />
  		  	   <form:hidden path="organization" />
  		       <form:hidden path="reportType" />
  		      
   
	</form:form>
	
	
	
 <%@ include file="/WEB-INF/jsp/include/footer.jsp" %> 		
</body>
</html>