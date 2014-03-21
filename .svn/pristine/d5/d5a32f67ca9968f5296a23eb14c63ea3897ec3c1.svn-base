(function($) {
	if($.fn.mask) {
		// Overload the date validator to account for the underscore in the masked input
		$.validator.addMethod("date", function(value, element) {
			// To account for the date mask the date patter can be any of the following
			// __/__/_____ or 01/__/_____ or 01/01/____ or 01/01/01__ or 01/01/2001
			var datePattern = /^([0_][1-9_]|[1_][0-2_])\/([0_][1-9_]|[12_][0-9_]|[3_][01_])\/(([1_][9_]|[2_][01_])[\d_]{2}|[\d_]{2}(__)?)$/;

			return this.optional(element) || datePattern.test(value);
		}, "Please enter a valid date.");
	}

	$.validator.addMethod("time", function(value, element) {
		var timePattern = /^(1[0-2]|0?[1-9]):[0-5]\d ?(a|p)m$/i;

		return this.optional(element) || timePattern.test(value);
	}, "Please enter a valid time.");

	$.validator.addMethod("percent", function(value, element) {
		return this.optional(element) || (!isNaN(value) && value >= 0.0 && value <= 100.0);
	}, "Please enter a valid percentage.");

	$.validator.addMethod("money", function(value, element) {
		var moneyPattern = /^\-?\$?[0-9]+(,[0-9]{3})*(\.[0-9]{2})?$/;

		return this.optional(element) || moneyPattern.test(value);
	}, "Please enter a valid dollar amount.");

	$.validator.addMethod("phone", function(value, element) {
		value = value.replace(/\s+/g, "");

		// Allowed udnerscore in the phone because the mask will put underscores in automatically
		var phonePattern = /^(1-?)?\(?[2-9_][\d_]{2}\)?-?[2-9_][\d_]{2}-?[\d_]{4}$/;

		return this.optional(element) || value.length > 9 && phonePattern.test(value);
	}, "Please enter a valid phone number");

	$.validator.addMethod("greaterOrEqual", function(endDateValue, endDateEl, startDateEl) {
		startDateEl = $(startDateEl);

		var startDateValue = startDateEl && startDateEl.size() ? startDateEl.val() : undefined;

		// If both values are empty or just the end date is empty return true
		if((!startDateValue && !endDateValue) || !endDateValue) {
			return true;
		}
		// If either value can not be converted to a date return false
		else if(/Invalid|NaN/.test(new Date(startDateValue)) || /Invalid|NaN/.test(new Date(endDateValue))) {
			return false;
		}

		// Return true if the end date is greater than or equal to the start date
		return new Date(endDateValue) >= new Date(startDateValue);
	}, "The end date must be greater than the start date.");

	$.validator.addMethod("search-results-required", function(value, element) {
		var valid = false;

		$(element).closest(".search-container")
			.find(".search-results input[type=hidden]")
			.each(function(index, element) {
				// If any of the hidden input fields have a value then return true
				if(element.value !== "") {
					valid = true;

					return;
				}
			});

		return valid;
	}, "This field is required.");

	// In the future use another class or attribute to determine what country code the postal code should be validated against.
	$.validator.addMethod("postal-code", function(value, element) {
		// Allowed udnerscore in the second part of the postal code because the mask will put underscores in automatically
		var postalCodePattern = /^\d{5}(-[\d_]{4})?$/;

		return this.optional(element) || postalCodePattern.test(value);
	}, "Please enter a valid postal code");
})(jQuery);