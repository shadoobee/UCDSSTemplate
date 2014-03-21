


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ include file="/WEB-INF/jsp/include/header.jsp" %>



       
       
    <script type="text/javascript">
 $(document).ready(function(){
		 
	$('#shib_display_name').click(function() {
		
		var index = $("#shib_display_name")[0].selectedIndex; 
		$('#shib_first_name option').eq(index).attr('selected', 'selected');
		$('#shib_last_name option').eq(index).attr('selected', 'selected');
		$('#shib_email_address option').eq(index).attr('selected', 'selected');
		$('#shib_common_name option').eq(index).attr('selected', 'selected');
		$('#shib-identity-provider option').eq(index).attr('selected', 'selected');
		$('#shib_userid option').eq(index).attr('selected', 'selected');
		$('#shib_ucnetid option').eq(index).attr('selected', 'selected');
		$('#shib_display_name option').eq(index).attr('selected', 'selected');
		$('#random_uuid_string option').eq(index).attr('selected', 'selected');
		$('#timestamp option').eq(index).attr('selected', 'selected');
	  
		
		 $("#first_name").val( $('select[id$=shib_first_name] :selected').text());
		 $("#last_name").val( $('select[id$=shib_last_name] :selected').text());
		 $("#identity-provider").val( $('select[id$=shib-identity-provider] :selected').text());
		 $("#email_address").val( $('select[id$=shib_email_address] :selected').text());
		 $("#userid").val( $('select[id$=shib_userid] :selected').text());
		 $("#common_name").val( $('select[id$=shib_common_name] :selected').text());
		 $("#ucNetId").val( $('select[id$=shib_ucnetid] :selected').text());
		 $("#ts").val( $('select[id$=timestamp] :selected').text());
		 $("#random").val( $('select[id$=random_uuid_string] :selected').text());
	
	});
 });
</script>   
</head>

 <body>
	
	<c:choose>
      <c:when test="${access eq false}">
         <h2><c:out value="${properties.noAccess}"/> </h2>
	  </c:when>  
     <c:otherwise>
		<c:choose>
		<c:when test="${spoof}"> 
	    <c:set var="spoofUser" value="${spoofUsers}" scope="session"/>
	 	<h2>Spoof</h2>
		<p>Please select a user and log in.</p>
		<form:form id="spoof" method="post" action="index.htm" modelAttribute="formBean" >	
		
  
    
    
   		 <table  align="center" class="spoof_table">
    
    
   		<tr>
		<td align="right"><strong>Display Name:</strong></td>
			<td><select name="shib_display_name" id="shib_display_name">
				<c:forEach var="user" items="${spoofUsers}">
					<option value="${user.shibDisplayName}">${user.shibDisplayName}</option>
				</c:forEach>
				</select>
		</td></tr>

<tr>
<td  align="right"><strong>First Name:</strong></td>

<td><input type ="text" id="first_name" name="first_name"  disabled="disabled" value="${spoofUser[0].shibFirstName }"/></td>
	<td><select style="visibility:hidden" name="shib_first_name" id="shib_first_name">
		<c:forEach var="user" items="${spoofUsers}">		
			<option value="${user.shibFirstName}">${user.shibFirstName}</option>
		</c:forEach>

</select>
</td></tr>

<tr>
<td  align="right"><strong>Last Name:</strong></td>
<td><input type ="text" id="last_name" name="last_name"  disabled="disabled" value="${spoofUser[0].shibLastName }"/></td>
	<td><select style="visibility:hidden" name="shib_last_name" id="shib_last_name">
		<c:forEach var="user" items="${spoofUsers}">
			<option value="${user.shibLastName}">${user.shibLastName}</option>
		</c:forEach>

</select>
</td></tr>

<tr>
<td  align="right"><strong>Identity Provider:</strong></td>
<td ><input style="width : 250px; type ="text" id="identity-provider" name="identity-provider"  disabled="disabled" value="${spoofUser[0].shibIdentityProvider }"/></td>
<td><select style="visibility:hidden" name="shib-identity-provider" id="shib-identity-provider">
	<c:forEach var="user" items="${spoofUsers}">
			<option value="${user.shibIdentityProvider}">${user.shibIdentityProvider}</option>
		</c:forEach>
</select>
</td></tr>


<tr>
<td align="right"><strong>Email Address:</strong></td>
<td ><input style="width : 250px; type ="text" id="email_address" name="email_address"  disabled="disabled" value="${spoofUser[0].shibEmailAddress }"/></td>
	<td><select name="shib_email_address" id="shib_email_address" style="visibility:hidden">
			<c:forEach var="user" items="${spoofUsers}">
				<option value="${user.shibEmailAddress}">${user.shibEmailAddress}</option>
			</c:forEach>
		</select>
</td></tr>

<tr>
<td  align="right"><strong>User ID:</strong></td>
<td ><input style="width : 250px; type ="text" id="userid" name="userid"  disabled="disabled" value="${spoofUser[0].shibUserId }"/></td>
	<td><select name="shib_userid" id="shib_userid" style="visibility:hidden">
			<c:forEach var="user" items="${spoofUsers}">
				<option value="${user.shibUserId}">${user.shibUserId}</option>
			</c:forEach>
		</select>
</td></tr>	



<tr>
<td  align="right"><strong>Common Name:</strong></td>
<td ><input style="width : 250px; type ="text" id="common_name" name="common_name"  disabled="disabled" value="${spoofUser[0].shibCommonName }"/></td>
	<td><select name="shib_common_name" id="shib_common_name"  style="visibility:hidden">
			<c:forEach var="user" items="${spoofUsers}">
				<option value="${user.shibCommonName}">${user.shibCommonName}</option>
			</c:forEach>
		</select>
</td></tr>


<tr>
<td align="right"><strong>UC Net ID:</strong></td>
    <td ><input style="width : 250px; type ="text" id="ucNetId" name="ucNetId"  disabled="disabled" value="${spoofUser[0].shibUcnetId }"/></td>
	<td><select name="shib_ucnetid" id="shib_ucnetid" style="visibility:hidden">
	  	<c:forEach var="user" items="${spoofUsers}">
			<option value="${user.shibUcnetId}">${user.shibUcnetId}</option>
		</c:forEach>
		</select>
</td></tr>




<tr>
<td align="right"><strong>Timestamp:</strong></td>
   <td ><input style="width : 250px; type ="text" id="ts" name="ts"  disabled="disabled" value="${spoofUser[0].shibTimestamp }"/></td>
	<td><select name="timestamp" id="timestamp"  style="visibility:hidden">
			<c:forEach var="user" items="${spoofUsers}">
				<option value="${user.shibTimestamp}">${user.shibTimestamp}</option>
			</c:forEach>
		</select>
</td></tr>

<tr>
<td  align="right"><strong>Random Id:</strong></td>
 <td ><input style="width : 250px; type ="text" id="random" name="random"  disabled="disabled" value="${spoofUser[0].shibRandomUuidString}"/></td>
	<td><select name="random_uuid_string" id="random_uuid_string" style="visibility:hidden">
			<c:forEach var="user" items="${spoofUsers}">
				<option value="${user.shibRandomUuidString}">${user.shibRandomUuidString}</option>
			</c:forEach>
		</select>
</td></tr>


<tr>




<tr><td colspan="2" align="center"><input class="button" type="submit" value="&nbsp;&nbsp;&nbsp;Log in&nbsp;&nbsp;&nbsp;" /></td>
</tr>
</table>


 </form:form>
 
 
    
    
  </c:when>  

  <c:otherwise>
    <div >

        <h2><a class="login" href="${properties.shibUrl}" title="Log in to Shibboleth!">Log in to LMS Reports (via shibboleth)</a></h2>
    </div>

</c:otherwise>
</c:choose>
</c:otherwise>
</c:choose>

	
   
	
	
	
