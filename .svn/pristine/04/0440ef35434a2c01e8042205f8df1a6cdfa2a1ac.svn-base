<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
            "http://www.w3.org/TR/html4/loose.dtd">

<html >
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<!-- stylesheets -->

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/typography.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/blue.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/sexybuttons.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/xbreadcrumbs.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/js/multiselect/jquery.multiselectdropdown.css">
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-ui.min.js"></script>
	

	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.framedialog.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.url.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.validate.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.maskedinput.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.placeholder.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/validate-additions.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/xbreadcrumbs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/validDate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/multiselect/jquery.multiselectdropdown.js"></script>
	<!-- 
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/multiselect/arc90_multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/multiselect/jquery-latest.js"></script>
     -->   
		<script type="text/javascript" charset="utf-8">
		$(function(){
			// handle datepicker
			$( ".date" ).datepicker({
				showOn: "button",
				buttonImage: "static/images/calendar.gif",
				buttonImageOnly: true,
				changeMonth: true,
				changeYear: true,
				dateFormat: 'mm/dd/yy'				
			});
			
			// date mask
			$("#fromDate").mask("99/99/9999");
			$("#toDate").mask("99/99/9999");
			
			
		
			
		
			
		});
		
		
		
		</script>	

	<script type="text/javascript">
	$(document).ready(function(){
        //  Initialize xBreadcrumbs
        $('#breadcrumbs-1').xBreadcrumbs();
       // $('#breadcrumbs-2').xBreadcrumbs({ collapsible: true });
       // $('#breadcrumbs-3').xBreadcrumbs();
	});
</script>	
 <title>LMS Reports</title>			
</head>
<body>
<div id="header">
	<!-- The actual logo images are background images. This makes the page more compatible with text-only browsers. -->
	<img id="print_logo" src="<%= request.getContextPath() %>/static/images/system_logo_on-blue.gif" alt="logo" width="250" height="27" />
 
</div>	
	

 
   
     <div id="app_title" style="color:white;">&nbsp;LMS Reports
      <div id="search_wrapper" style="color:white;"><%= (session.getAttribute("user")==null)? "Not Logged In" : session.getAttribute("user") %> | <%= ("Admin".equals(session.getAttribute("role"))) ? "<a href='listusers.htm'>Admin</a> | " : "" %><a href="mailto:erm@ucop.edu" title="Contact Us">Contact Us</a></div>
      </div> 
  
	

    

	