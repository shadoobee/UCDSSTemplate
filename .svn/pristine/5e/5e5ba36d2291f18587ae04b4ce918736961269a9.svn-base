<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>



<!-- <script src="http://code.jquery.com/jquery.js"></script> -->
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/bootstrap.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/sorttable.js"></script>


<script type="text/javascript">
	$(function(){
		
		$.ajaxSetup({ cache: false });
		
		$('#user_modal_form #save').click(function() {
			
			var errors = 0;
			
 			if($('#user_modal_form #firstName').val() == "") {
				$('#user_modal_form #firstName').parents('.control-group').addClass("error");
				$("#user_modal_form #firstName").focus();
				errors++;
			} else {
				$('#user_modal_form #firstName').parents('.control-group').removeClass("error");
			}
			
			if($('#user_modal_form #lastName').val() == "") {
				$('#user_modal_form #lastName').parents('.control-group').addClass("error");
				$('#user_modal_form #lastName').focus();
				errors++;
			} else {
				$('#user_modal_form #lastName').parents('.control-group').removeClass("error");
			}
			
			if($('#user_modal_form #email').val() == "") {
				$('#user_modal_form #email').parents('.control-group').addClass("error");
				$('#user_modal_form #email').focus();
				errors++;
			} else {
				$('#user_modal_form #email').parents('.control-group').removeClass("error");
			}

			if($('#user_modal_form #campusId').val() == "") {
				$('#user_modal_form #campusId').parents('.control-group').addClass("error");
				$('#user_modal_form #campusId').focus();
				errors++;
			} else {
				$('#user_modal_form #campusId').parents('.control-group').removeClass("error");
			}
			
			if($('#user_modal_form #location_id').val() == "") {
				$('#user_modal_form #location_id').parents('.control-group').addClass("error");
				$('#user_modal_form #location_id').focus();
				errors++;
			} else {
				$('#user_modal_form #location_id').parents('.control-group').removeClass("error");
			}
			
 			if($('#user_modal_form #roleId').val() == "") {
				$('#user_modal_form #roleId').parents('.control-group').addClass("error");
				$('#user_modal_form #roleId').focus();
				errors++;
			} else {
				$('#user_modal_form #roleId').parents('.control-group').removeClass("error");
			}
			
 			if($('#user_modal_form #roleId').val() == 4 && $('#user_modal_form #activities').val() == "") {
				$('#user_modal_form #activities').parents('.control-group').addClass("error");
				$('#user_modal_form #activities').focus();
				errors++;
			} else {
				$('#user_modal_form #activities').parents('.control-group').removeClass("error");
			}
			
			
			if(errors == 0) {
				$('#user_modal_form #user_form_error').slideUp();

		 		$.post('updateuser.htm', 
		 				$('#userForm').serialize(), 
		 				function(data) {
		 					if(data.status == "success") {
								window.location.href = "listusers.htm";
		 					}
		 					else if(data.status == "error") {
								alert("An error occured while trying to process your request.");
		 					}
		 					else {
								alert("Something bad is happening...");
		 					}
						},
						"json"
		 		)
			} else {
				$('#user_modal_form #error_count').html(errors);
				$('#user_modal_form #user_form_error').slideDown();
			}
		});
		
		
		$('#delete').click(function() {
			
			$.getJSON('deleteuser.htm?personId=' + $('#delete_confirmation #user_to_delete_id').val(), function(data) {
				window.location.href = "listusers.htm";
			});
			
		});
		
		
		$("#user_modal_form").on("hidden", function() { 
			$('#user_modal_form #userForm')[0].reset();
			$('#user_modal_form #personId').val(0);
			$('#user_modal_form .control-group').removeClass("error");
			$('#user_modal_form #error_count').html(0);
			$('#user_modal_form #user_form_error').slideUp();
		});
		
		
		$('#user_modal_form #campusId').change(function() {
			populateDomains($('#user_modal_form #campusId').val());
		})
		
		
		$('#user_modal_form #roleId').change(function() {
			if($('#user_modal_form #roleId').val() == 4)
				$('#user_modal_form #activities').parents('.control-group').slideDown();
			else {
				$('#user_modal_form #activities').parents('.control-group').slideUp();
				$('#user_modal_form #activities').parents('.control-group').removeClass("error");
			}
		})

/* 		
		$('#user_modal_form #email').tooltip({
			placement: 'right',
			trigger: 'focus', 
			title: 'Email is used for authentication.'
		});
		
		$('#user_modal_form #ucnetId').tooltip({
			placement: 'right',
			trigger: 'focus', 
			title: 'UCNetID is used for authentication if email fails.'
		});
		
		$('#user_modal_form #activities').tooltip({
			placement: 'right',
			trigger: 'focus', 
			title: 'Separate activity codes by commas, spaces, or new lines.'
		});
 */		
	});
	
	
	function populateDomains(campusId, location_id) {
		$('#user_modal_form #loading').show();
		$('#user_modal_form #location_id').empty();
		$('#user_modal_form #location_id')
			.append($("<option></option>")
			.attr("value", "")
			.text("- select domain -")); 
		
		
	 		$.getJSON('getdomains.htm?campusId=' + campusId, function(data) {
			
			$.each(data, function(i, domain) {   
				$('#user_modal_form #location_id')
					.append($("<option></option>")
					.attr("value", domain.domainId)
					.text(domain.domainNamePadded));
			});
			
			if(location_id != null)
				$('#user_modal_form #location_id').val(location_id);
			
			$('#user_modal_form #loading').hide();
		});
	 	
	}
	
	
	function deleteConfirm(personId, firstName, lastName) {
		$('#delete_confirmation #user_to_delete').html(firstName + " " + lastName);
		$('#delete_confirmation #user_to_delete_id').val(personId);
		$('#delete_confirmation').modal('show');
	}
	
	
	function editUser(personId) {
		$("#user_modal_form .modal-title").html("Edit User");

		$.getJSON('getuserinfo.htm?personId=' + personId, function(data) {
			populateDomains(data.campusId, data.location_id);
			
			$('#user_modal_form #personId').val(data.personId);
			$('#user_modal_form #firstName').val(data.firstName);
			$('#user_modal_form #lastName').val(data.lastName);
			$('#user_modal_form #email').val(data.email);
			$('#user_modal_form #ucnetId').val(data.ucnetId);
			$('#user_modal_form #campusId').val(data.campusId);
			$('#user_modal_form #roleId').val(data.roleId);
			
 			var activities = "";
			
 			if($('#user_modal_form #roleId').val() == 4) {
 				$('#user_modal_form #activities').parents('.control-group').show();
				for( i = 0; i < data.activities.length; i++) {
					if( i==0 )	activities = data.activities[i];
					else		activities = activities + ", " + data.activities[i];
				}
 			} else {
 				$('#user_modal_form #activities').parents('.control-group').hide();
 				$('#user_modal_form #activities').parents('.control-group').removeClass("error");
 			}
 			
			$('#user_modal_form #activities').val(activities);
			
			$('#user_modal_form').modal('show');
		});
		
		
	}
	
	
	function addUser() {
		$("#user_modal_form .modal-title").html("Add New User");
		$('#user_modal_form #activities').parents('.control-group').hide();
		$('#user_modal_form #activities').parents('.control-group').removeClass("error");
		$('#user_modal_form').modal('show');
	}
	
	
</script>


<div>
	<ul class="xbreadcrumbs" id="breadcrumbs-1">
		<li>
			<a href="index.htm" class="home">Home</a>
		</li>
		<li>
			<a class="active">Admin Page</a>
		</li>
	</ul>
</div>

<c:if test="${isAdmin}">

	<h2>Administrator Page</h2>
	
	<a onclick="addUser();" href="#" class="btn btn-primary btn-small"><i class="icon-user icon-white"></i> Add new user</a>
	
	<br /><br />
	
	<table class="table table-striped table-hover table-bordered table-condensed sortable" style="width: auto;">
		<thead>
			<tr>
				<th style="cursor: pointer;">Name</th>
				<th style="cursor: pointer;">Email</th>
				<th style="cursor: pointer;">UCNetID</th>
				<!-- <th style="cursor: pointer;">Location</th> -->
				<th style="cursor: pointer;">Role</th>
				<!-- <th style="cursor: pointer;">Activities</th> -->
				<th class="sorttable_nosort"></th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="user" items="${users}">
				
				<tr>
					<td>
						<button class="btn btn-link btn-small" onclick="editUser(${user.id});">${user.firstName} ${user.lastName}</button>
					</td>
					<td>${user.email}</td>
					<td>${user.ucnetId}</td>
					<%-- <td>${user.location.locationName}</td> --%>
					<td>${user.roleName}</td>
					<%-- <td>
						<c:forEach var="activity" items="${user.activityList}">
							${activity.activityCd},
						</c:forEach>
					</td> --%>
					<td>
						<button class="btn btn-success btn-small" onclick="editUser(${user.id});"><i class="icon-pencil icon-white"></i></button>
						<button class="btn btn-danger btn-small" onclick="deleteConfirm(${user.id}, '${user.firstName}', '${user.lastName}');"><i class="icon-trash icon-white"></i></button>
					</td>
				</tr>
				
			</c:forEach>
			
		</tbody>

	</table>
	
	<table class="table table-striped table-bordered table-condensed" style="width: auto">
		<caption>Role Legend</caption>
		<thead>
			<tr>
				<th>Role</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="role" items="${userForm.roleList}">
				
				<tr>
					<td>${role.roleName}</td>
					<td>${role.roleDesc}</td>
				</tr>
				
			</c:forEach>
			
		</tbody>

	</table>

	<div id="user_modal_form" class="modal hide fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">---</h4>
				</div>
				<div class="modal-body" style="max-height: 800px">
					<div class="alert alert-error" id="user_form_error" style="display: none">
						You have <span id="error_count"></span> errors on your form.
					</div>
					<form:form action="updateuser.htm" method="POST" modelAttribute="userForm"  class="form-horizontal">
						<form:hidden path="personId" />
						<div class="control-group">
							<label class="control-label" for="firstName">First Name</label>
							<div class="controls">
								<form:input path="firstName" placeholder="First Name" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="lastName">Last Name</label>
							<div class="controls">
								<form:input path="lastName" placeholder="Last Name" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="email">Email</label>
							<div class="controls">
								<form:input path="email" placeholder="Email" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="ucnetId">UCNetID</label>
							<div class="controls">
								<form:input path="ucnetId" placeholder="UCNetID" class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="campusId">Campus</label>
							<div class="controls">
								<form:select path="campusId" class="form-control" >
									<form:option value="">- select campus -</form:option>
									<form:options items="${userForm.campusList}" itemLabel="locationName" itemValue="topLevelLMSKey" />
								</form:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="location_id">Domain</label>
							<div class="controls">
								<form:select path="location_id" class="form-control" >
									<form:option value="">- select domain -</form:option>
									<form:options items="${userForm.locationList}" itemLabel="domainNamePadded" itemValue="domainId" />
								</form:select>
								<img id="loading" src="<%=request.getContextPath() %>/static/images/wait30.gif" style="display: none" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="roleId">Role</label>
							<div class="controls">
								<form:select path="roleId" class="form-control" >
									<form:option value="">- select role -</form:option>
									<form:options items="${userForm.roleList}" itemLabel="roleName" itemValue="roleID" />
								</form:select>
							</div>
						</div>
						<div class="control-group" style="display: none">
							<label class="control-label" for="activities">Activity Codes</label>
							<div class="controls">
								<form:textarea path="activities" placeholder="Activity Codes" class="form-control"></form:textarea>
							</div>
						</div>
					</form:form>
					
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal">Cancel</button>
					<button id="save" class="btn btn-primary"><i class="icon-hdd icon-white"></i> Save changes</button>
				</div>
			</div>
		</div>
	</div>

	<div id="delete_confirmation" class="modal hide fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Are you sure?</h4>
				</div>
				<div class="modal-body alert">
					<input type="hidden" id="user_to_delete_id" />
					Are you sure you want to delete <span id="user_to_delete"></span>?
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal">Cancel</button>
					<button id="delete" class="btn btn-danger"><i class="icon-trash icon-white"></i> Delete User</button>
				</div>
			</div>
		</div>
	</div>

</c:if>

<br />

<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>

</body>
</html>
