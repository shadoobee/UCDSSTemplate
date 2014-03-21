<script type="text/javascript">
$(function(){	

	$("#activityType").multiselectdropdown({ selectedItemsText: '*', noneSelectedText: "Select Activity Type "});

	// add multiple select / deselect functionality
    $("#selectall").click(function () {
    	$('input:checkbox[name=activityId]').attr('checked', this.checked);
    
    });
 
    // if all checkbox are selected, check the selectall checkbox
    // and viceversa
    $('input:checkbox[name=activityId]').click(function(){
       
        if($('input:checkbox[name=activityId]').length == $('input:checkbox[name=activityId]:checked').length) {
            $("#selectall").attr("checked", "checked");
        } else {
            $("#selectall").removeAttr("checked");
        }
 
    });
	

	$("#selectedCampus").change(function(){
		 $("#reportType").val("changeCampus");
    	 $("form").attr("target", "_self");
		 this.form.submit();

	     });

	$(":button").click(function(){
			
		if($('input:checkbox[name=activityId]:checked').length > 200 && this.id != "activities"){
			alert("Please select less than 200 activities");
			return;
		}
		
		if(!ValidateForm(this.form)){
			return;
		}
		
		if(this.id == "activities"){
			 $("#reportType").val("findActivities");
			 $("form").attr("target", "_self");
			this.form.submit();
			return;
		}
		
		 var act =$('input:checkbox[name=activityId]:checked').val();
	      //valid activity?
	        if(act == null || act.length== 0){
			alert("Please select an activity!");
			return;
	        }
		
		if(this.id == "excel"){
		    $("#organization").val( $('select[id$=selectedDomain] :selected').text());
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
