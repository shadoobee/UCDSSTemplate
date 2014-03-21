package edu.ucdavis.ss.lmsreports.web.form.validator;

import org.springframework.validation.Errors;

import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import edu.ucdavis.ss.lmsreports.ui.ParameterPageUi;

public class RosterActivityValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ParameterPageUi.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "activityId", "learningActivity.required");

	}

}
