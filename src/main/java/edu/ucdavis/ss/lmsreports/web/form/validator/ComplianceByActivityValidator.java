package edu.ucdavis.ss.lmsreports.web.form.validator;



import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;



public class ComplianceByActivityValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ParameterPageUi.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		/* ParameterPageUic = (ComplianceByActivityUi) target;
		   if(c.getFromDate() == null || c.getToDate() == null){
			   return;
		   }
		   
		   if(c.getFromDate().length() == 0 && c.getToDate().length() == 0){
			   return;
		   }
		   GregorianCalendar fromCal = new GregorianCalendar();
		   GregorianCalendar toCal = new GregorianCalendar();
	      try{
	     
	      fromCal.setLenient(false);
	      String[] userFromDate = c.getFromDate().split("/");
	      if (userFromDate.length ==3){
	    	  fromCal.set(Calendar.YEAR, Integer.parseInt(userFromDate[2]));  
	    	  fromCal.set(Calendar.MONTH, Integer.parseInt(userFromDate[0])-1);  
	    	  fromCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(userFromDate[1]));
	    	  fromCal.getTime(); 
	      } else{
	    	  throw new Exception();
	      }
	   	  
	      }catch (Exception e){
	   	  
	   	  errors.rejectValue("fromDate", "fromDate.format");
	   	  return;
	      }
	      
	      try{
	         
	          toCal.setLenient(false);
	          String[] userToDate = c.getToDate().split("/");
	          if (userToDate.length ==3){
	        	  toCal.set(Calendar.YEAR, Integer.parseInt(userToDate[2]));  
	        	  toCal.set(Calendar.MONTH, Integer.parseInt(userToDate[0])-1);  
	        	  toCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(userToDate[1]));
	        	  toCal.getTime(); 
	          } else{
	        	  throw new Exception();
	          }
	       	  
	          }catch (Exception e){
	       	  
	       	  errors.rejectValue("toDate", "toDate.format");
	       	  return;
	          }
	      
	   
	      
	      long diff =  toCal.getTimeInMillis()-fromCal.getTimeInMillis(); 
	      long diffDays = diff/(24 *60 * 60* 1000);
	      
	      if(diffDays > 365){
	    	  errors.rejectValue("toDate", "toDate.range");
	      }*/
		
	}
	

}
