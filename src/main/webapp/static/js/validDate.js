// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function FutureDate(formObj){
	
	var fdt=formObj.fromDate;
    var tdt=formObj.toDate;
    
    
	 if(fdt.value.length ==0 && tdt.value.length == 0){
         return true;
      }
	 
	
	 var today = new Date();
	 var fromDate = new Date(formObj.fromDate.value);
	 var toDate = new Date(formObj.toDate.value);
	
	 if(toDate < today){
		 alert ("Date must be in the future");
		 tdt.focus();
		 return false;
		 
	 }
	 
	 if(fromDate < today){
		 alert ("Date must be in the future");
		 fdt.focus();
		 return false;
		 
	 }
	
	return true;
}


function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31;
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30;}
		if (i==2) {this[i] = 29;}
   } 
   return this;
}

function isDate(dtStr){
   
	var daysInMonth = DaysArray(12);
	var pos1=dtStr.indexOf(dtCh);
	var pos2=dtStr.indexOf(dtCh,pos1+1);
	var strMonth=dtStr.substring(0,pos1);
	var strDay=dtStr.substring(pos1+1,pos2);
	var strYear=dtStr.substring(pos2+1);
	strYr=strYear;

	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
	}
	month=parseInt(strMonth);
	day=parseInt(strDay);
	year=parseInt(strYr);
	
	if (pos1==-1 || pos2==-1){
		alert("The date format should be : mm/dd/yyyy");
		return false;
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Please enter a valid month");
		return false;
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Please enter a valid day");
		return false;
	}

	if (year<minYear || year>maxYear){
		alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear);
		return false;
	}
	//if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
	//	alert("Please enter a valid date");
	//	return false;
	//}
return true;
}

function ValidateForm(formObj){
   
	if(formObj.id == "rosterParam" && formObj.fromDate == null){
		return true;
	}
	var fdt=formObj.fromDate;
    var tdt=formObj.toDate;
   
    
   if(formObj.id != "complianceParamLearner"){
	 if(formObj.courseCode != null){  
       var activityCd=formObj.courseCode.value;
	    if( activityCd.indexOf("\'") >= 0 || activityCd.indexOf('"') >= 0 ){
	    	alert("Quotes are not allowed");
	   	return false;
	   } 
	 }
   }
    
    if(formObj.id == "complianceParam" || formObj.id ==  "learningActivityParam" || formObj.id ==  "upcomingActivitiesParam" || formObj.id ==  "statusParam" ){
        if(fdt.value.length ==0 && tdt.value.length == 0){
           return true;
        }
   }
	if(isDate(fdt.value)==false){
	 fdt.focus();
	  return false;
	}
	
	
	if(isDate(tdt.value)==false){
	 tdt.focus();
	  return false;
	}
	
	
    return true;
 }



