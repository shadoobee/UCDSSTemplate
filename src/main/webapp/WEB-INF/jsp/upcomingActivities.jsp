

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 <%@ include file="/WEB-INF/jsp/include/header.jsp" %>
 <%@ include file="/WEB-INF/jsp/include/incl_header.jsp" %>






<div>
 	<ul class="xbreadcrumbs" id="breadcrumbs-1">
     <li>
        <a href="index.htm" class="home">Home</a>
     </li>
     <li>
       <a class="active">Upcoming Activities Parameter Page</a>
      </li>
    </ul>
    
</div>







	<form:form id="upcomingActivitiesParam"  name="upcomingActivitiesParam" method="POST" modelAttribute="formBean">
	

	
	<h2>Upcoming Activities Report</h2>
	 <%@ include file="/WEB-INF/jsp/include/incl_basic_param_page.jsp" %>  
  		      
      
   
	</form:form>
	
	
<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>  		
</body>
</html>