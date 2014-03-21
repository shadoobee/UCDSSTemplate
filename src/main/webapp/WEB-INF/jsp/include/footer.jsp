<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="footer">
	<div id="nav_bottom">
		<p>
			Copyright &copy; The Regents of the University of California,
            Davis campus. All Rights Reserved.
            <br/>
            Privileged and Confidential. Do not disclose.
            <br/>
            <c:out value = "${buildDetailsService.buildProjectName}"/> ${buildDetailsService.buildVersionNumber}, built:${buildDetailsService.buildDateString}
		</p>
	</div>
</div>