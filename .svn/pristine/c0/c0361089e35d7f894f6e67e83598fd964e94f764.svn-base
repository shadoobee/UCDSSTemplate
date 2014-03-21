package edu.ucdavis.ss.lmsreports.web.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;





public class RosterValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate", "fromDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate", "toDate.required");
		
	}


}
