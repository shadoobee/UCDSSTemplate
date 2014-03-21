

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>


<ul class="xbreadcrumbs" id="breadcrumbs-1">
     <li>
        <a class="active" >Home</a>
     </li>
    </ul>
<div>

<div id="floatMiddle">
<h3>Please select a report:</h3>


<form:form id="mainlist"  method="get" modelAttribute="formBean" >



 <table class="main_table" cellspacing="20">
<tr>
<c:forEach items="${formBean}" var="report" varStatus="loop">
 <c:if test="${not loop.first and loop.index % 2 == 0}">
      </tr><tr>
    </c:if>
    <c:set var="link" value="?report=" />
    <td > <a href='<c:out value="${report.page}${link}${report.reportFile}" />' class="main_table"> <c:out value="${report.reportName}"/> </a>
      <br /> <c:out value="${report.reportDesc}"/> 
       </td>
</c:forEach>

</tr>
</table>
</div>
 <div id="floatRight">
<div id="announcements">
Announcement: <br>
Maintenance is performed nightly from 1AM to 4AM.  During this time LMS Reports is unavailable.
</div>
</div>
</div>



</form:form>
<div id = "floatMiddle">
<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
</div>
</body>

</html>	