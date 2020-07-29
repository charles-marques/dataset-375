package ecms.web.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import ecms.domain.BaseUser;

public class StoreManagerValidator {

	public void validate(BaseUser sm, Errors errors) {
		
		if (!StringUtils.hasText(sm.getFirstName())) {
			errors.rejectValue("firstName", "error.required", "required");
		} else if(sm.getFirstName().length() > 50) {
			errors.rejectValue("firstName", "error.length", "length");
		
		}
		
		if(!StringUtils.hasText(sm.getLastName())) { 
			errors.rejectValue("lastName", "error.required", "required");
		} else if(sm.getLastName().length() > 50) {
			errors.rejectValue("lastName", "error.length", "length");
		}
		
		if(!StringUtils.hasText(sm.getLogin())) {
			errors.rejectValue("login", "error.required", "required");
		} else if(sm.getLogin().length() > 50) {
			errors.rejectValue("login", "error.length", "length");
		}
		
		if(!StringUtils.hasText(sm.getPassword())) {
			errors.rejectValue("password", "error.required", "required");
		} else if(sm.getPassword().length() > 50) {
			errors.rejectValue("password", "error.length", "length");
		}
		
		if(StringUtils.hasText(sm.getAddress().getStreetAddress1())) {
			if(sm.getAddress().getStreetAddress1().length() > 100) {
				errors.rejectValue("address.streetAddress1", "error.length", "length");
			}
		}
		
		if(StringUtils.hasText(sm.getAddress().getStreetAddress2())) {
			if(sm.getAddress().getStreetAddress2().length() > 100) {
				errors.rejectValue("address.streetAddress2", "error.length", "length");
			}
		}
		
		if(StringUtils.hasText(sm.getAddress().getCity())) {
			if(sm.getAddress().getCity().length() > 100) {
				errors.rejectValue("address.city", "error.length", "length");
			}
		}
		
		if(StringUtils.hasText(sm.getAddress().getZip())) {
			if(sm.getAddress().getZip().length() > 100) {
				errors.rejectValue("address.zip", "error.length", "length");
			}
		}
		
		if(StringUtils.hasText(sm.getAddress().getCountry())) {
			if(sm.getAddress().getCountry().length() > 100) {
				errors.rejectValue("address.country", "error.length", "length");
			}
		}

	}
	
}
